package com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PodContainer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1235970030039534403L;
	private String containerName;
	private String cpuCores;
	private String memoryBytes;
}
