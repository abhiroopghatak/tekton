package com.abhiroop.kubetime.svc;

import java.util.List;

import com.abhiroop.kubetime.pojo.Cluster;
import com.abhiroop.kubetime.pojo.UserClusterAccess;

public interface IuserClusterAccess {

	List<UserClusterAccess> getActiveLabelAccessPerUser(long userid);
	UserClusterAccess addLabelAccessToUser(String label , long userid);
	UserClusterAccess removeLabelAccessToUser(String label , long userid);
	UserClusterAccess addClusterAccessToUser(Cluster c , String label , long userid);
	UserClusterAccess clusterAccessRequest(UserClusterAccess uca);
}
