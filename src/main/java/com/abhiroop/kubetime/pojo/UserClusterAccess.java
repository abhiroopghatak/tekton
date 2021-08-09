package com.abhiroop.kubetime.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter @ToString
public class UserClusterAccess implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2393226741308062030L;

	@Id
	private long uuid;
	private long userUniqueId;
	private long clusterUniqueId;
	private String userClusterId;
	private String accessedLabel;
	private Date lastUpdated;
	private String status;
	
}
