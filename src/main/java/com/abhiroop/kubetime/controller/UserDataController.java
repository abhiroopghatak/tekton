package com.abhiroop.kubetime.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.pojo.UserClusterAccess;
import com.abhiroop.kubetime.svc.IUserInfoService;
import com.abhiroop.kubetime.svc.IuserClusterAccess;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserDataController {

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
}
