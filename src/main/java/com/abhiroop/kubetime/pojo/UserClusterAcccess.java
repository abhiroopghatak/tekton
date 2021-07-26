package com.abhiroop.kubetime.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter @Setter @ToString
public class UserClusterAcccess implements Serializable{ /**
	 * 
	 */
	private static final long serialVersionUID = -2393226741308062030L;

	@Id
	private long uuid;
	private String userEmail;
	private long clusterUniqueId;
	private String userClusterId;
	
}
