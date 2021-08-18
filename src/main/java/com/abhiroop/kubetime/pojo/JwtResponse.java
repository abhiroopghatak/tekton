package com.abhiroop.kubetime.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter @NoArgsConstructor 
public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private  String jwttoken;
	
	private String useremail;
	private String role;

	public JwtResponse(String jwttoken, String useremail, String role) {
		super();
		this.jwttoken = jwttoken;
		this.useremail = useremail;
		this.role=role;
	}
	
	

	}