package com.abhiroop.kubetime.svc.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhiroop.kubetime.config.SystemConstants;
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
	public List<UserClusterAccess> getAllLabelAccessPerUser(long userid) {
		
		
		return userClusterAccessRepo.findAll(Sort.by("clusterUniqueId"));
	}
	@Override
	public List<UserClusterAccess> getActiveLabelAccessPerUser(long userid) {
		List<UserClusterAccess> ucaList =userClusterAccessRepo.getAllPerUser(userid);
		if (!CollectionUtils.isEmpty(ucaList)) {

			Iterator<UserClusterAccess> itr = ucaList.iterator();
			while (itr.hasNext()) {
				UserClusterAccess uca = itr.next();
				if (!StringUtils.equals("A", uca.getStatus())) {
					System.out.print(uca + " removed from response list cause its no more active");
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
	public UserClusterAccess clusterAccessRequest(UserClusterAccess ucar) {
		List<UserClusterAccess> ucaList = userClusterAccessRepo.getAllPerUser(ucar.getUserUniqueId());
		if (!CollectionUtils.isEmpty(ucaList)) {

			Iterator<UserClusterAccess> itr = ucaList.iterator();
			while (itr.hasNext()) {
				UserClusterAccess uca = itr.next();
				if (StringUtils.equals(ucar.getAccessedLabel(), uca.getAccessedLabel())
						&& ucar.getClusterUniqueId() == uca.getClusterUniqueId()) {
					System.out.print("@clusterAccessRequest : Dup;licate request for label access");
					throw new RuntimeException("Duplicate request");
				}
			}
		}

		return userClusterAccessRepo.save(ucar);
	}

	@Override
	public List<UserClusterAccess> getAllRequetsedStatus() {
		List<UserClusterAccess> ucaList = userClusterAccessRepo.findAll();
		Iterator<UserClusterAccess> uItr = ucaList.iterator();

		while (uItr.hasNext()) {
			UserClusterAccess uca = uItr.next();
			if (!StringUtils.equals(SystemConstants.RequestedStatus, uca.getStatus())) {
				uItr.remove();
			}
		}
		return ucaList;
	}

	@Override
	public UserClusterAccess statusUpdate(UserClusterAccess uca, String status) throws Exception {

		Optional<UserClusterAccess> ucaOptional = userClusterAccessRepo.findById(uca.getUuid());
		if (ucaOptional.isPresent()) {
			uca = ucaOptional.get();
			uca.setLastUpdated(new Date());
			uca.setStatus(status);
			uca = userClusterAccessRepo.saveAndFlush(uca);
		} else {
			throw new RuntimeException("Bad Request . No request object found to Approve/Reject");
		}
		return uca;
	}

}
