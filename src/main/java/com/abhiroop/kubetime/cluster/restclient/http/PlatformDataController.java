package com.abhiroop.kubetime.cluster.restclient.http;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.OpenshiftClient;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.ClusterMetadata;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.NamespaceResourceObject;
import com.abhiroop.kubetime.cluster.restclient.http.svc.PlatformDataServiceImpl;

import antlr.NameSpace;

@RestController
@RequestMapping("/api/platforms")
public class PlatformDataController {
	// test integration with ocp ... need to refactor

	@Autowired
	private PlatformDataServiceImpl platformDataService;

	@GetMapping("/getPlatformSpec")
	public ClusterMetadata getPlatformSpec(ClusterMetadata cmd) {

		ClusterClientBaseBuilder oc = new OpenshiftClient();

		oc.withBaseUrl(cmd.getEndPointUrl());
		oc.usingToken(cmd.getToken());
		ClusterMetadata data = platformDataService.getPlatformSpec(oc);
		data.setClusterName(cmd.getClusterName());
		return data;
	}

	public List<NamespaceResourceObject> getNameSpaceResources(ClusterMetadata cmd, List<String> s) {
		List<NamespaceResourceObject> nroList = new ArrayList<NamespaceResourceObject>();

		if (!CollectionUtils.isEmpty(s)) {
			ClusterClientBaseBuilder oc = new OpenshiftClient();

			oc.withBaseUrl(cmd.getEndPointUrl());
			oc.usingToken(cmd.getToken());
			for (String label : s) {
				try {
					List<String> namespaces = platformDataService.getAccessibleNameSPaces(oc, label);
					if (!CollectionUtils.isEmpty(namespaces)) {
						for (String namespace : namespaces) {
							NamespaceResourceObject nro = new NamespaceResourceObject();
							nro.setNamespaceName(namespace);
							nro.setLabelSelector(label);
							try {
								nro = platformDataService.getResourceQuota(oc, nro);
								nro = platformDataService.getVolumePerNamespace(oc, nro);
							} catch (RestClientException | ParseException rce) {
								System.out.println(
										"error in getting resources for namespace: " + namespace + ". Details: " + rce);
								if (rce instanceof ParseException) {

									nro.setErrorMessage("Data Parcing exception.");
								} else {
									nro.setErrorMessage("Api error occurred.");
								}
							}
							nroList.add(nro);
						}
					}
				} catch (Exception e) {
					System.out.print("error in getting accessible namespaces for cluster: " + cmd.getClusterId()
							+ ". Details:" + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		return nroList;
	}
}
