package com.abhiroop.kubetime.svc;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.abhiroop.kubetime.pojo.User;

public interface IUserInfoService extends UserDetailsService{

	
	User getUserById(long uuid);
	User  deActivateUser(User usr);
	User getOneByEmail(String email);
	User signUpUser(User user, boolean isUpdate) throws Exception;
}
