package com.abhiroop.kubetime.cluster.restclient.http.svc;

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

}

