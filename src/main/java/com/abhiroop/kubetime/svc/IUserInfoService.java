package com.abhiroop.kubetime.svc;

import com.abhiroop.kubetime.pojo.User;

public interface IUserInfoService {

	
	User getUserById(long uuid);
	User  deActivateUser(User usr);
	User getOneByEmail(String email);
	User signUpUser(User user, boolean isUpdate);
}
