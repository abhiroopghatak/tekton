package com.abhiroop.kubetime.cluster.restclient.http.pojo.txn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OpenshiftRespone {

	private String kind;
    private String apiVersion;
    private Metadata metadata;
    private String status;
}
