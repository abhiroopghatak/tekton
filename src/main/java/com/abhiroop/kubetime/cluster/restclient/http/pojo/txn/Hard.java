package com.abhiroop.kubetime.cluster.restclient.http.pojo.txn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Hard {

	
	private String limitsCpu;
    private String limitsMemory;
    private String persistentvolumeclaims;
    private String pods;
    private String requestsStorage;
}
