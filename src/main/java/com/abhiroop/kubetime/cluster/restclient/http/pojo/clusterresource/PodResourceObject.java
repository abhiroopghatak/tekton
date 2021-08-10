package com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString 
public class PodResourceObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4145123969387696265L;

	
	
	private String podName;
	List<PodContainer> containers;
	
}
