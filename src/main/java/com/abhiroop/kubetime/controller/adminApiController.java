package com.abhiroop.kubetime.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abhiroop.kubetime.config.SystemConstants;
import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.ResponsePojo;
import com.abhiroop.kubetime.svc.ClusterInfoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class adminApiController {

	private static final Logger log = LoggerFactory.getLogger(adminApiController.class);

	@Autowired
	private ClusterInfoService clusterInfoService;

	@PostMapping("/cluster/add")
	public ResponsePojo addCluster(@RequestBody Cluster c) {

		System.out.println("@/cluster/add: Received request to create cluster =>" + c);
		ResponsePojo ar = null;
		if (c != null && c.getUuid() == 0) {

			c.setRegisteredon(new Date());
			c.setStatus(SystemConstants.StatusActive);
			try {
				c = clusterInfoService.addCluster(c);

				if (c.getUuid() > 0) {
					ar = new ResponsePojo(HttpStatus.ACCEPTED, SystemConstants.EntitySavedInDBSUCCESS, c);
					log.info("@/cluster/add==== Admin added new cluster into system =======");
				}
			} catch (Exception e) {
				c.setErrorMessage("Operation =AddCluster= thrown exception.");
				ar = new ResponsePojo(HttpStatus.CONFLICT, SystemConstants.EntitySavedInDBFAILURE, c);
				log.debug("@/cluster/add ====cluster add thrown exception=====" + e.getMessage());
			}

		}

		return ar;
	}
}
