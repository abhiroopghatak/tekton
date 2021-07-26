package com.abhiroop.kubetime.cluster.restclient.http.pojo;

public interface IntegrationStatus {

	public static final String SUCCESS = "Success";
    public static final String FAILURE = "Failure";

    String getMessage();

    int getCode();

    String getStatus();

    boolean isFailure();

    boolean isSuccess();
    
}
