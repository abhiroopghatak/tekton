package com.abhiroop.kubetime.cluster.restclient.http.svc;

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
import com.abhiroop.kubetime.cluster.restclient.utils.JsonParserHelper;
@Service
public class PlatformMetadataServiceImpl implements IPlatformMetadataService {

	private static final Logger log = LoggerFactory.getLogger(PlatformMetadataServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public int getClusterInfo(ClusterClientBaseBuilder client) {
		int result = 0;
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getClusterService Status using Spring RESTTemplate START =======");
		try {

			ResponseEntity<String> response = restTemplate.exchange(
					client.getBaseUrl() + ClusterConstants.RestApiRelativePath.CLUSTER_SERVICE, HttpMethod.GET, entity,
					String.class);

			result = response.getStatusCodeValue();
			log.info("@ "+ClusterConstants.RestApiRelativePath.CLUSTER_SERVICE+"=======responded with httpStatus="+result);
		} catch (RestClientException rce) {
			log.error(rce.getMessage());
		}
		return result;
	}

	
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
}

