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
@Getter
@Setter
@ToString
@Table(name = "_costlist")
public class ItemCost implements Serializable {

	/**
	 
	 */
	private static final long serialVersionUID = -4881492616406082505L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long uuid;
	
	private long clusterid;
	private String currency ="dollar";
	private String cpuunit="1 vCPU";
	private String memoryunit = "1 Gi";
	private String storageunit = "1 Gi";
	private String timelengthunit = "month";
	private double cpucost ;
	private double momorycost;
	private double storagecost;
	private Date lastupdated;
}
