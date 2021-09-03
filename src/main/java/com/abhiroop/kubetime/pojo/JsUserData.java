package com.abhiroop.kubetime.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JsUserData implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6070335194768177956L;
	private String fullname;
	private String email; 
	private String role ;
	private String lastUpdated;
	private String status;
	private List<JsClusterAccess> calist;
	
	
}
