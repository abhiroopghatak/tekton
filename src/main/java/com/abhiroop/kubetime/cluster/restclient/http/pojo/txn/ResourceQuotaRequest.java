package com.abhiroop.kubetime.cluster.restclient.http.pojo.txn;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResourceQuotaRequest {
	private String kind;
    private String apiVersion;
    private Metadata metadata;
    private Spec spec;
}
