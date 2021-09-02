package com.abhiroop.kubetime.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ChangePasswordRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7888412513422470489L;

	private String useremail;
	private String oldpassword;
	private String newpassword;
	
	
}
