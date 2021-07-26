package com.abhiroop.kubetime.maintest;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.ClusterClientBaseBuilder;
import com.abhiroop.kubetime.cluster.restclient.http.pojo.OpenshiftClient;

public class RoughTest {

	public static void main(String ...a) {
		System.out.println("TEST STARTS ");
		
		RoughTest rt = new RoughTest();
		rt.applyClusterDetailTest();
		
	}


	String applyClusterDetailTest(){
		
		
		
		ClusterClientBaseBuilder op= new OpenshiftClient();
		op.withBaseUrl("kk://oop.lop");
		System.out.println(op);
		return "F";
	}
	
}
