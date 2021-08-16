package com.abhiroop.kubetime.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.config.SystemConstants;
import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.repo.UserRepo;
import com.abhiroop.kubetime.svc.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	UserRepo usrRepository;

	@Override
	public User getUserById(long uuid) {
		return usrRepository.getById(uuid);
	}

	@Override
	public User deActivateUser(User usr) {
		usr.setStatus("I");
		usr = usrRepository.saveAndFlush(usr);
		return usr;
	}

	@Override
	public User getOneByEmail(String email) {
		User u = usrRepository.getByEmail(email);
		return u;
	}

	@Override
	public User signUpUser(User user, boolean isUpdate) {
		User userWithDuplicateUsername = getOneByEmail(user.getEmail());
		if (!isUpdate) {
			if (userWithDuplicateUsername != null) {

				System.err.print("User with email " + user.getEmail() + "already exists in system");
				throw new RuntimeException("Email already exists");
			}else {
				user.setRole(SystemConstants.END_USER_ROLE);
				user.setStatus(SystemConstants.UserSIgnUpRequestedStatus);
				user = usrRepository.save(user);
			}
		} 
		
		return user;
	}

}
