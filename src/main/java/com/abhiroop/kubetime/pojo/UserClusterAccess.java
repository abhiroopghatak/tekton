package com.abhiroop.kubetime.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter @ToString
@Table(name="_user_cluster_access")
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
