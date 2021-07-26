package com.abhiroop.kubetime.cluster.restclient.utils;

import org.apache.commons.lang.StringUtils;

import com.abhiroop.kubetime.cluster.restclient.http.pojo.IntegrationStatus;

public class ClusterIntegrationException extends RuntimeException{

	 private static final long serialVersionUID = -7076942050102006278L;
	    private IntegrationStatus status;

	    public ClusterIntegrationException(Throwable cause, String message, Object... arguments) {
	        super(String.format(message, arguments), cause);
	    }

	    public ClusterIntegrationException(String message, Object... arguments) {
	        this(null, null, message, arguments);
	    }

	    public ClusterIntegrationException(Throwable cause, IntegrationStatus status, String message, Object... arguments) {
	        super(String.format(StringUtils.defaultIfBlank(message, ""), arguments), cause);
	        this.status = status;
	    }

	    public IntegrationStatus getStatus() {
	        return this.status;
	    }

	    public boolean hasStatus() {
	        return this.status != null;
	    }

	    @Override
	    public String getMessage() {
	        if (hasStatus()) {
	            return super.getMessage() + " " + status.getMessage();
	        }
	        return super.getMessage();
	    }

	    
}
