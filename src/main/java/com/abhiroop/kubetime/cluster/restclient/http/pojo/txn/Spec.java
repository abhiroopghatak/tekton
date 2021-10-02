package com.abhiroop.kubetime.cluster.restclient.http.pojo.txn;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Spec {
	private List<String> finalizers = new ArrayList<String>();
	private Hard hard;
    
}
