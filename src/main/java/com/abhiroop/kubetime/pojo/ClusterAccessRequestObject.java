package com.abhiroop.kubetime.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ClusterAccessRequestObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4165439957171997512L;
	private long uuid;
	private String useremail;
	private String userName;
	private String clusterName;
	private String environment;
	private String requestedLabel;
	
}
