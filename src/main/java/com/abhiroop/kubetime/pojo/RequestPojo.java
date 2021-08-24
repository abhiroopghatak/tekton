package com.abhiroop.kubetime.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class RequestPojo {

	
	
	private long uuid;
	
	private String action;
	private String message;
	private Object obj;
	
}
