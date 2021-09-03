package com.abhiroop.kubetime.svc;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.pojo.UserClusterAccess;

public interface IUserInfoService extends UserDetailsService {

	User getUserById(long uuid);

	User userStatusUpdate(long uuid, String status) throws Exception;

	User getOneByEmail(String email);


	List<User> getAll();
	List<User> getAllNonActive();

	User saveUser(User user, boolean isUpdate) throws Exception;
}
