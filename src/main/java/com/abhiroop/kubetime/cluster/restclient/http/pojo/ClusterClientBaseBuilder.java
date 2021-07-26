package com.abhiroop.kubetime.cluster.restclient.http.pojo;

import com.abhiroop.kubetime.cluster.restclient.http.constants.IHttpConstants;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.TimeUnit;


@Getter @ToString
public abstract class ClusterClientBaseBuilder {

	
	private String baseUrl;
    private String token;
    private String clusterType;
    private int readTimeout = IHttpConstants.DEFAULT_READ_TIMEOUT;
    private TimeUnit readTimeoutUnit = TimeUnit.MILLISECONDS;
    private int connectTimeout = IHttpConstants.DEFAULT_READ_TIMEOUT;
    private TimeUnit connectTimeoutUnit = TimeUnit.MILLISECONDS;
    private int writeTimeout = IHttpConstants.DEFAULT_READ_TIMEOUT;
    private TimeUnit writeTimeoutUnit = TimeUnit.MILLISECONDS;
    
    public ClusterClientBaseBuilder() {
    }

    public ClusterClientBaseBuilder withBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
    
    public ClusterClientBaseBuilder usingToken(String token) {
        this.token = token;
        return this;
    }
    
    public ClusterClientBaseBuilder clusterType(String clusterType) {
        this.clusterType = clusterType;
        return this;
    }
    
    public ClusterClientBaseBuilder withConnectTimeout(int timeout, TimeUnit unit) {
        this.connectTimeout = timeout;
        this.connectTimeoutUnit = unit;
        return this;
    }
    
    public ClusterClientBaseBuilder withConnectTimeout(int connectInMillis) {
        this.connectTimeout = connectInMillis;
        return this;
    }
    
    public ClusterClientBaseBuilder withReadTimeout(int connectInMillis) {
        this.readTimeout = connectInMillis;
        return this;
    }
    
    public abstract String getClusterSpecApiUrl();
}
