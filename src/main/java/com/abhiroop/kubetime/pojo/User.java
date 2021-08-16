package com.abhiroop.kubetime.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Entity
@Table(name="_user")
@Getter @Setter @ToString
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -271557119267516389L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	private String email;
	private String fullname;
	private String clusterUserId;
	private Date createTime;
	private String role;
	private String pwd;
	private String status;
	
	public User() {
		super();
	}
}
