package com.abhiroop.kubetime.svc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.UserClusterAccess;
import com.abhiroop.kubetime.repo.UserClusterAccessRepo;
import com.abhiroop.kubetime.svc.IuserClusterAccess;

@Service
@Transactional
public class UserClusterAccessService implements IuserClusterAccess {

	@Autowired
	private UserClusterAccessRepo userClusterAccessRepo;

	@Override
	public List<UserClusterAccess> getAllPerUser(long userid) {
		
		return userClusterAccessRepo.getAllPerUser(userid);
		
	}

	@Override
	public UserClusterAccess addLabelAccessToUser(String label, long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserClusterAccess removeLabelAccessToUser(String label, long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserClusterAccess addClusterAccessToUser(Cluster c, String label, long userid) {
		// TODO Auto-generated method stub
		return null;
	}

}
