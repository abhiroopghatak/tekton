package com.abhiroop.kubetime.cluster.restclient.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.OpenshiftClient;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.ClusterMetadata;
import com.abhiroop.kubetime.cluster.restclient.http.svc.PlatformDataServiceImpl;

@RestController
@RequestMapping("/api/platforms")
public class PlatformDataController {
	//test integration with ocp ... need to refactor
	
	
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
}
