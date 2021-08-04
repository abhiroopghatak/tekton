package com.abhiroop.kubetime.maintest;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.OpenshiftClient;

public class RoughTest {

	


	String applyClusterDetailTest(){
		
		
		
		ClusterClientBaseBuilder op= new OpenshiftClient();
		op.withBaseUrl("kk://oop.lop");
		System.out.println(op);
		return "F";
	}
	
}
