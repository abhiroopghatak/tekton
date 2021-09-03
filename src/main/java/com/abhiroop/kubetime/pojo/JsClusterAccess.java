package com.abhiroop.kubetime.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsClusterAccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 623881718298947917L;
	private String clustername;
	private String labelSelector;
	private String status;

	public JsClusterAccess(String cname, String ls, String status) {
		this.clustername = cname;
		this.status = status;
		this.labelSelector = ls;
	}
}
