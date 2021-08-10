package com.abhiroop.kubetime.cluster.restclient.http.svc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.abhiroop.kubetime.cluster.restclient.http.constants.openshift4.ClusterConstants;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.ClusterMetadata;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.NamespaceResourceObject;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.PodContainer;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource.PodResourceObject;
import com.abhiroop.kubetime.cluster.restclient.utils.JsonParserHelper;

@Service
public class PlatformDataServiceImpl implements IPlatformDataService {

	private static final Logger log = LoggerFactory.getLogger(PlatformDataServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ClusterMetadata getPlatformSpec(ClusterClientBaseBuilder client) {
		String fullJson = null;
		ClusterMetadata cmd = new ClusterMetadata();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getClusterSpec using Spring RESTTemplate START =======");
		try {

			ResponseEntity<String> response = restTemplate.exchange(
					client.getBaseUrl() + ClusterConstants.RestApiRelativePath.CLUSTER_METADATA, HttpMethod.GET, entity,
					String.class);
			fullJson = response.getBody();
			JSONObject jo = (JSONObject) new JSONParser().parse(fullJson);

			cmd.setBuildDate(JsonParserHelper.getDataValue(fullJson, new String[] { "buildDate" }));
			cmd.setGitVersion(JsonParserHelper.getDataValue(fullJson, new String[] { "gitVersion" }));
			cmd.setGoVersion(JsonParserHelper.getDataValue(fullJson, new String[] { "goVersion" }));
			cmd.setPlatform(JsonParserHelper.getDataValue(fullJson, new String[] { "platform" }));
			response = null;
			response = restTemplate.exchange(client.getClusterSpecApiUrl(), HttpMethod.GET, entity, String.class);
			fullJson = response.getBody();
			jo = (JSONObject) new JSONParser().parse(fullJson);

			cmd.setChannel(JsonParserHelper.getDataValue(fullJson, new String[] { "spec", "channel" }));
			cmd.setClusterVersion(
					JsonParserHelper.getDataValue(fullJson, new String[] { "status", "desired", "version" }));
			cmd.setClusterId(JsonParserHelper.getDataValue(fullJson, new String[] { "spec", "clusterID" }));

			System.out.println(cmd);

		} catch (RestClientException | ParseException rce) {
			System.out.println(rce);
			if (rce instanceof ParseException) {

				cmd.setErrorMessage("Data Parcing exception occurred. Connect Admin");
			} else {
				cmd.setErrorMessage("Api error occurred. Connect Admin");
			}
		}
		log.info("==== RESTful API Response using Spring RESTTemplate END =======");
		return cmd;
	}

	public List<String> getAccessibleNameSPaces(ClusterClientBaseBuilder client, String labelKeyValue)
			throws RestClientException, ParseException {
		String fullJson = null;
		List<String> nameList = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getAccessibleNameSPaces using Spring RESTTemplate START =======");

		ResponseEntity<String> response = restTemplate.exchange(client.getBaseUrl()
				+ ClusterConstants.RestApiRelativePath.CLUSTER_NAMESPACE_LIST + "?labelSelector=" + labelKeyValue,
				HttpMethod.GET, entity, String.class);
		fullJson = response.getBody();
		if (!StringUtils.isEmpty(fullJson)) {
			nameList = JsonParserHelper.getNameSpaceNames(fullJson);
		}
		log.info("==== RESTful API Response using Spring RESTTemplate END =======");
		return nameList;
	}

	@Override
	public NamespaceResourceObject getVolumePerNamespace(ClusterClientBaseBuilder client,
			NamespaceResourceObject namespace) throws RestClientException, ParseException {
		String fullJson = null;
		String volumeReserved = "0Gi";
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getVolumePerNamespace using Spring RESTTemplate START =======");

		ResponseEntity<String> response = restTemplate.exchange(
				client.getBaseUrl() + ClusterConstants.RestApiRelativePath.CLUSTER_NAMESPACE_LIST + "/"
						+ namespace.getNamespaceName() + "/persistentvolumeclaims",
				HttpMethod.GET, entity, String.class);
		fullJson = response.getBody();
		if (!StringUtils.isEmpty(fullJson)) {
			volumeReserved = JsonParserHelper.getNameSpacePvc(fullJson);
			namespace.setStorageVolume(volumeReserved);
			log.info("==== Namespace " + namespace + " using " + volumeReserved + " od volume storage. =======");
		}
		log.info("==== RESTful API Response for getVolumePerNamespace using Spring RESTTemplate END =======");

		return namespace;
	}

	@Override
	public NamespaceResourceObject getResourceQuota(ClusterClientBaseBuilder client, NamespaceResourceObject namespace)
			throws RestClientException, ParseException {

		String fullJson = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getResourceQuota using Spring RESTTemplate START =======");

		ResponseEntity<String> response = restTemplate
				.exchange(
						client.getBaseUrl() + ClusterConstants.RestApiRelativePath.CLUSTER_NAMESPACE_LIST + "/"
								+ namespace.getNamespaceName() + "/resourcequotas",
						HttpMethod.GET, entity, String.class);
		fullJson = response.getBody();
		if (!StringUtils.isEmpty(fullJson)) {

			JSONArray items = JsonParserHelper.getItemsFromResource(fullJson, "items");
			if (items != null && items.size() > 0) {
				Object o = items.get(0);
				String itemJsonString = o.toString();
				namespace.setRequestCpu(JsonParserHelper.getDataValue(itemJsonString,
						new String[] { "status", "hard", "requests.cpu" }));
				namespace.setRequestMemory(JsonParserHelper.getDataValue(itemJsonString,
						new String[] { "status", "hard", "requests.memory" }));
				namespace.setUsedCpu(JsonParserHelper.getDataValue(itemJsonString,
						new String[] { "status", "used", "requests.memory" }));
				namespace.setUsedMemory(JsonParserHelper.getDataValue(itemJsonString,
						new String[] { "status", "used", "requests.memory" }));
				log.info("==== NamespaceResourceObject " + namespace + "  =======");
			}
		}
		log.info("==== RESTful API Response for getResourceQuota using Spring RESTTemplate END =======");
		return namespace;
	}

	@Override
	public List<PodResourceObject> getPodResourcePerNameSpace(ClusterClientBaseBuilder client, String namespace)
			throws Exception {
		List<PodResourceObject> proList = new ArrayList<>();

		String fullJson = null;

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getPodResourcePerNameSpace using Spring RESTTemplate START =======");

		ResponseEntity<String> response = restTemplate.exchange(client.getBaseUrl()
				+ ClusterConstants.RestApiRelativePath.CLUSTER_NAMESPACE_POD_RESORCE_PREFIX + "/" + namespace + "/pods",
				HttpMethod.GET, entity, String.class);
		fullJson = response.getBody();
		if (!StringUtils.isEmpty(fullJson)) {

			JSONArray items = JsonParserHelper.getItemsFromResource(fullJson, "items");
			if (items != null && items.size() > 0) {

				for (Object o : items) {

					String itemJsonString = o.toString();
					PodResourceObject pro = new PodResourceObject();

					pro.setPodName(JsonParserHelper.getDataValue(itemJsonString, new String[] { "metadata", "name" }));
					pro = setContainersDataToPod(itemJsonString, pro);

					log.info("==== PodResourceObject " + pro + "  =======");
					proList.add(pro);
				}
			}
		}
		log.info("==== RESTful API Response for getResourceQuota using Spring RESTTemplate END =======");

		return proList;
	}

	private PodResourceObject setContainersDataToPod(String itemJsonString, PodResourceObject pro)
			throws ParseException {
		JSONArray containers = JsonParserHelper.getItemsFromResource(itemJsonString, "containers");
		pro.setContainers(new ArrayList<>());
		if (containers != null && containers.size() > 0) {

			for (Object container : containers) {
				PodContainer pc = new PodContainer();
				pc.setContainerName(JsonParserHelper.getDataValue(container.toString(), new String[] { "name" }));
				pc.setCpuCores(JsonParserHelper.getDataValue(container.toString(), new String[] { "usage", "cpu" }));
				pc.setMemoryBytes(
						JsonParserHelper.getDataValue(container.toString(), new String[] { "usage", "memory" }));
				pro.getContainers().add(pc);
			}

		}
		return pro;
	}

}
