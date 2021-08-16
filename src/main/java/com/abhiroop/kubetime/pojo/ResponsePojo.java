package com.abhiroop.kubetime.pojo;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class ResponsePojo implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -833862715102681540L;
	private HttpStatus status;
	private String message;
	private Object obj;
}
