package com.abhiroop.kubetime.cluster.restclient.http.pojo.clusterresource;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NamespaceResourceObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4666716379891692361L;
	private String errorMessage;
	private String namespaceName;
	private String labelSelector;
	private String storageVolume;
	private String requestCpu;
	private String requestMemory;
	private String usedCpu;
	private String usedMemory;

}
