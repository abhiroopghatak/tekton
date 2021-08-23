package com.abhiroop.kubetime.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceRequestObject {

	private String clusterId;
	private String userEmail;
	private String namespace;
	private String sessionId;

}
