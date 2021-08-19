package com.abhiroop.kubetime.config;

public interface SystemConstants {

	
	public static final String StatusActive ="A";
	public static final String StatusInActive ="I";
	public static final String RequestedStatus ="R";
	
	
	public static final String UserClusterAccessRequestedStatus ="R";
	
	public static final String EntitySavedInDBSUCCESS ="S";
	public static final String EntitySavedInDBFAILURE ="F";
	
	
	public static final String END_USER_ROLE ="EU";
	public static final String ADMIN_USER_ROLE ="AU";
	
	
	public static final String[] unAuthUrls = new String[]{"/login", "/sign-up", "/api/authenticate", "/manifest.json"}; 
}
