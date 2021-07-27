package com.abhiroop.kubetime.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.cluster.restclient.http.ClusterDataServiceImpl;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.OpenshiftClient;
import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.svc.ClusterInfoService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/clusters")
public class ClusterDataControlller {

	
	@Autowired
	private ClusterInfoService clusterInfoService;
	
	
	@Autowired
	private ClusterDataServiceImpl clusterDataService;
	
	@GetMapping("/health")
	public String health() {
		return "OK";
	}
	
	@GetMapping("/getOne/{id}")
	public Cluster getById(@PathVariable long id) {
		return clusterInfoService.getClusterById(id);
	}
	
	@GetMapping("/getAllByUser/{email}")
	public List<Cluster>  getAllByUserEmail(@PathVariable String userEmail) {
		return clusterInfoService.getClusterListOfUser(userEmail);
	}
	
	@GetMapping("/getAll")
	public List<Cluster>  getAll() {
		return clusterInfoService.getClusterLis();
	}
	
	
	//test integration with ocp ... need to refactor
	@GetMapping("/getClusterSpec")
	public String getClusterSpec() {
		
		
		ClusterClientBaseBuilder oc = new OpenshiftClient();
		
		oc.withBaseUrl("https://api.openshift4.ocppocmtc311.com:6443/");
		oc.withConnectTimeout(9000);
		oc.usingToken("sha256~kc9VDV2ZU9ZdpJxVSFhyRx4gfNj1Y8uVuLzA2sLFdMk");
		String data = clusterDataService.getClusterSpec(oc);
		return data;
	}
}
