package com.abhiroop.kubetime.cluster.restclient.http.svc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.abhiroop.kubetime.cluster.restclient.http.constants.openshift4.ClusterConstants;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.txn.NamespaceRequestObject;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.txn.OpenshiftRespone;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.txn.ProjectRequest;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.txn.ResourceQuotaRequest;

public class UserClusterDataServiceImpl implements IUserClusterDataService {

	private static final Logger log = LoggerFactory.getLogger(UserClusterDataServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String createNameSpace(ClusterClientBaseBuilder client, ProjectRequest pr) {
		String result = ClusterConstants.ResourceStatus.CREATED_FAILURE;
		String url = client.getBaseUrl() + ClusterConstants.NamespaceRestApiPath.PROJECT_REQUEST;
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<ProjectRequest> request = new HttpEntity<>(pr, headers);
		ResponseEntity<OpenshiftRespone> response = restTemplate.postForEntity(url, request, OpenshiftRespone.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			log.info("Request Successful");
			result = ClusterConstants.ResourceStatus.CREATED_SUCCESS;
		}
		return result;
	}

	@Override
	public String delNameSpace(ClusterClientBaseBuilder client, String name) {
		String result = ClusterConstants.ResourceStatus.CREATED_FAILURE;
		String url = client.getBaseUrl() + ClusterConstants.NamespaceRestApiPath.PROJECT_DELETE;
		HttpEntity<String> entity = getSandardHttpEntity(client);

		ResponseEntity<OpenshiftRespone> response = restTemplate.exchange(url, HttpMethod.DELETE, entity,
				OpenshiftRespone.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			log.info("Request Successful");
			result = ClusterConstants.ResourceStatus.CREATED_SUCCESS;
		}
		return result;
	}

	private HttpEntity<String> getSandardHttpEntity(ClusterClientBaseBuilder client) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}

	@Override
	public String addLabelToNamespace(ClusterClientBaseBuilder client, String namespaceName, String label) {

		String result = ClusterConstants.ResourceStatus.CREATED_FAILURE;

		NamespaceRequestObject nro = new NamespaceRequestObject();
		nro.setApiVersion("v1");
		nro.setKind("Namespace");
		nro.getMetadata().setName(namespaceName);
		nro.getMetadata().getLabels().setLabel(label);

		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<NamespaceRequestObject> requestUpdate = new HttpEntity<>(nro, headers);

		String resourceUrl = client.getBaseUrl() + ClusterConstants.NamespaceRestApiPath.NAMESPACE_EDIT + namespaceName;

		ResponseEntity<OpenshiftRespone> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, requestUpdate,
				OpenshiftRespone.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			log.info("Request Successful");
			result = ClusterConstants.ResourceStatus.CREATED_SUCCESS;
		}
		return result;
	}

	@Override
	public String createResourceQuota(ClusterClientBaseBuilder client, ResourceQuotaRequest rqo, String namespaceName) {
		String result = ClusterConstants.ResourceStatus.CREATED_FAILURE;
		String url = client.getBaseUrl() + ClusterConstants.NamespaceRestApiPath.PROJECT_QUOTA
				+ rqo.getMetadata().getNamespace() + "/resourcequotas";
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(client.getToken());
		headers.add("Accept", "application/json");
		headers.add("content-type", "application/json");
		HttpEntity<ResourceQuotaRequest> request = new HttpEntity<>(rqo, headers);
		ResponseEntity<OpenshiftRespone> response = restTemplate.postForEntity(url, request, OpenshiftRespone.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			log.info("Request Successful");
			result = ClusterConstants.ResourceStatus.CREATED_SUCCESS;
		}
		return result;
	}

}
