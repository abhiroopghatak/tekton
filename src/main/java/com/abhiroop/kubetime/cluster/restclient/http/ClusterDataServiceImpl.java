package com.abhiroop.kubetime.cluster.restclient.http;

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

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.utils.JsonParserHelper;


@Service
public class ClusterDataServiceImpl implements IClusterDataService {

	private static final Logger log = LoggerFactory.getLogger(ClusterDataServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String getClusterSpec(ClusterClientBaseBuilder client) {
//		restTemplate.
		String fullJson = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		log.info("==== RESTful API call getClusterSpec using Spring RESTTemplate START =======");
//		Response response = restTemplate.getForObject  (client.getClusterSpecApiUrl(), Response.class);
		try {
			ResponseEntity<String> response= restTemplate.exchange(client.getClusterSpecApiUrl(), HttpMethod.GET,entity,String.class);
		fullJson=response.getBody();
		//fullJson = response.getBody().toString();
		JSONObject jo = (JSONObject) new JSONParser().parse(fullJson);
		System.out.println(JsonParserHelper.getDataValue(fullJson, new String[]{"spec"}));
		System.out.println(JsonParserHelper.getDataValue(fullJson, new String[]{"status","desired","version"}));
		

		}catch(RestClientException | ParseException rce) {
			System.out.println(rce);
		}
		log.info(fullJson);
		log.info("==== RESTful API Response using Spring RESTTemplate END =======");
		return fullJson;
	}

}
