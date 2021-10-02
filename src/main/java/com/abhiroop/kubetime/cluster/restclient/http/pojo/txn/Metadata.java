package com.abhiroop.kubetime.cluster.restclient.http.pojo.txn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Metadata {

	private String name;
	private String namespace;
	
	private Labels labels;
   
}
