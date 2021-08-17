package com.abhiroop.kubetime.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.config.SystemConstants;
import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.ResponsePojo;
import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.pojo.UserClusterAccess;
import com.abhiroop.kubetime.svc.IUserInfoService;
import com.abhiroop.kubetime.svc.IuserClusterAccess;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserDataController {

	@Autowired
	private ClusterInfoControlller clusterCtrl;

	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IuserClusterAccess userClusterAccessService;

	@GetMapping("/getOne/{email}")
	public User getByEmail(@PathVariable String email) {

		return userInfoService.getOneByEmail(email);
	}

	public User getUserById(long userId) {
		return userInfoService.getUserById(userId);
	}

	public List<String> getActiveLabelAccessPerUser(Cluster c, long userid) {

		List<String> labelList = new ArrayList<>();
		List<UserClusterAccess> ucaList = userClusterAccessService.getActiveLabelAccessPerUser(userid);

		if (!CollectionUtils.isEmpty(ucaList)) {
			System.out.println("Total " + ucaList.size() + " userClusterAccess Entry  recieved from service");
			for (UserClusterAccess userClusterAccess : ucaList) {
				if (userClusterAccess.getClusterUniqueId() == c.getUuid()) {
					labelList.add(userClusterAccess.getAccessedLabel());
				}
			}
		}
		System.out.println("Total " + labelList.size() + " number of label accessed to the user of id : " + userid);
		return labelList;
	}

	@PostMapping("/requestAccess")
	public ResponsePojo raiseClusterACcess(@RequestBody UserClusterAccess uca) {

		ResponsePojo rp = new ResponsePojo();
		rp.setMessage(SystemConstants.EntitySavedInDBFAILURE);
		if (uca != null && uca.getClusterUniqueId() != 0) {
			User usr = getByEmail(uca.getJsUserEmail());
			Cluster c = clusterCtrl.getById("" + uca.getClusterUniqueId());
			if (c != null && usr != null && StringUtils.equals(usr.getStatus(), SystemConstants.UserStatusActive)) {
				uca.setUserUniqueId(usr.getUuid());
				uca.setStatus(SystemConstants.UserClusterAccessRequestedStatus);

				uca = userClusterAccessService.clusterAccessRequest(uca);
				rp.setMessage(SystemConstants.EntitySavedInDBSUCCESS);
			}
		}

		return rp;
	}

	@PostMapping("/register")
	public ResponsePojo create(@RequestBody User user) {
		System.out.println("received request to create user =>" + user);
		ResponsePojo ar = null;
		boolean isUpdate = false;
		try {
			user = userInfoService.signUpUser(user, isUpdate);
			if (!isUpdate && user.getUuid() == 0) {
				ar = new ResponsePojo(HttpStatus.CONFLICT, SystemConstants.EntitySavedInDBFAILURE, user);
			} else if (!isUpdate && user.getUuid() >= 0) {
				ar = new ResponsePojo(HttpStatus.ACCEPTED, SystemConstants.EntitySavedInDBSUCCESS, user);
			}

		} catch (Exception re) {
			ar = new ResponsePojo(HttpStatus.BAD_REQUEST, SystemConstants.EntitySavedInDBFAILURE, user);
			re.printStackTrace();
		}
		return ar;
	}
}
