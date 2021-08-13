package com.abhiroop.kubetime.svc.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
	public List<UserClusterAccess> getActiveLabelAccessPerUser(long userid) {
		List<UserClusterAccess> ucaList = userClusterAccessRepo.getAllPerUser(userid);
		if (!CollectionUtils.isEmpty(ucaList)) {

			Iterator<UserClusterAccess> itr = ucaList.iterator();
			while (itr.hasNext()) {
				UserClusterAccess uca = itr.next();
				if (!StringUtils.equals("A",uca.getStatus())) {
					System.out.print(uca +" removed from response list cause its no more active");
					itr.remove();
				}
			}
		}

		return ucaList;

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
	
	@Override
	public UserClusterAccess clusterAccessRequest(UserClusterAccess uca) {
		// TODO Auto-generated method stub
		return userClusterAccessRepo.save(uca);
	}

}
