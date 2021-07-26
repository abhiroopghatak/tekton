package com.abhiroop.kubetime.cluster.restclient.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace.Response;
import org.springframework.web.client.RestTemplate;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;

public class ClusterDataServiceImpl implements IClusterDataService {

	private static final Logger log = LoggerFactory.getLogger(ClusterDataServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String getClusterSpec(ClusterClientBaseBuilder client) {
		RestTemplate restTemplate = new RestTemplate();
		String fullJson = null;
		log.info("==== RESTful API call getClusterSpec using Spring RESTTemplate START =======");
		Response response = restTemplate.getForObject(client.getClusterSpecApiUrl(), Response.class);
		fullJson = response.toString();
		log.info(fullJson);
		log.info("==== RESTful API Response using Spring RESTTemplate END =======");
		return fullJson;
	}

}
