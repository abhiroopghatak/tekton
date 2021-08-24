package com.abhiroop.kubetime.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.config.SystemConstants;
import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.ClusterAccessRequestObject;
import com.abhiroop.kubetime.pojo.RequestPojo;
import com.abhiroop.kubetime.pojo.ResponsePojo;
import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.pojo.UserClusterAccess;
import com.abhiroop.kubetime.svc.ClusterInfoService;
import com.abhiroop.kubetime.svc.IUserInfoService;
import com.abhiroop.kubetime.svc.IuserClusterAccess;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
public class adminApiController {

	private static final Logger log = LoggerFactory.getLogger(adminApiController.class);

	@Autowired
	private ClusterInfoService clusterInfoService;
	@Autowired
	private IuserClusterAccess userClusterAccessService;
	@Autowired
	private IUserInfoService userInfoService;

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

				c.setErrorMessage("Operation =AddCluster= thrown exception." + e.getMessage());
				ar = new ResponsePojo(HttpStatus.CONFLICT, SystemConstants.EntitySavedInDBFAILURE, c);
				log.debug("@/cluster/add ====cluster add thrown exception=====" + e.getMessage());
			}

		}

		return ar;
	}

	@GetMapping("/user/new/requests")
	public List<User> getAllRequestedUser() {
		System.out.println("@/user/new/requests requested.");
		return userInfoService.getAllRequetsedStatus();

	}

	@PostMapping("/user/new/approve")
	public ResponsePojo newUserAction(@RequestBody RequestPojo rp) {
		System.out.println("@/user/new/approve requested.");
		ResponsePojo resp = null;
		String action = null;
		if (StringUtils.equals("Y", rp.getAction())) {
			action = SystemConstants.StatusActive;
		} else if (StringUtils.equals("N", rp.getAction())) {
			action = SystemConstants.StatusInActive;
		}
		User u;
		try {
			u = userInfoService.userStatusUpdate(rp.getUuid(), action);
			if (u != null) {
				resp = new ResponsePojo(HttpStatus.ACCEPTED, SystemConstants.EntitySavedInDBSUCCESS, u);
			} else {
				resp = new ResponsePojo(HttpStatus.FORBIDDEN, SystemConstants.EntitySavedInDBFAILURE, u);
			}
		} catch (Exception e) {
			resp = new ResponsePojo(HttpStatus.FORBIDDEN, SystemConstants.EntitySavedInDBFAILURE, e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return resp;
	}

	@GetMapping("/cluster/access/requests")
	public List<ClusterAccessRequestObject> getAllClusterRequests() {

		List<ClusterAccessRequestObject> cList = new ArrayList<>();
		List<UserClusterAccess> ucaList = userClusterAccessService.getAllRequetsedStatus();
		Cluster c = null;
		User u = null;
		for (UserClusterAccess uca : ucaList) {
			c = clusterInfoService.getClusterById(uca.getClusterUniqueId());
			u = userInfoService.getUserById(uca.getUserUniqueId());
			if (c == null || u == null) {
				continue;
			}
			if (StringUtils.equals(SystemConstants.StatusInActive, c.getStatus())
					|| StringUtils.equals(SystemConstants.StatusInActive, u.getStatus())) {
				continue;
			}
			ClusterAccessRequestObject caro = new ClusterAccessRequestObject();
			caro.setUuid(uca.getUuid());
			caro.setRequestedLabel(uca.getAccessedLabel());
			caro.setUseremail(u.getEmail());
			caro.setUserName(u.getFullname());
			caro.setClusterName(c.getName());
			caro.setEnvironment(c.getEnvironment());
			cList.add(caro);
		}

		return cList;
	}

	@PostMapping("/cluster/access/approve")
	public ResponsePojo approveCLusterAccess(@RequestBody ClusterAccessRequestObject caro) {
		ResponsePojo rp = null;

		UserClusterAccess uca = new UserClusterAccess();
		try {
			uca.setUuid(caro.getUuid());
			userClusterAccessService.statusUpdate(uca, SystemConstants.StatusActive);
			rp = new ResponsePojo(HttpStatus.ACCEPTED, SystemConstants.EntitySavedInDBSUCCESS, uca);
		} catch (Exception e) {
			rp = new ResponsePojo(HttpStatus.FORBIDDEN, SystemConstants.EntitySavedInDBFAILURE, uca);
			System.out.println("@/cluster/access/approve Exception=> " + e.getMessage());
		}
		return rp;
	}

	@PostMapping("/cluster/access/reject")
	public ResponsePojo rejectCLusterAccess(@RequestBody ClusterAccessRequestObject caro) {
		ResponsePojo rp = null;

		UserClusterAccess uca = new UserClusterAccess();
		try {
			uca.setUuid(caro.getUuid());
			userClusterAccessService.statusUpdate(uca, SystemConstants.StatusInActive);
			rp = new ResponsePojo(HttpStatus.ACCEPTED, SystemConstants.EntitySavedInDBSUCCESS, uca);
		} catch (Exception e) {
			rp = new ResponsePojo(HttpStatus.FORBIDDEN, e.getMessage(), uca);
			System.out.println("@/cluster/access/reject Exception=> " + e.getMessage());
		}
		return rp;
	}
}
