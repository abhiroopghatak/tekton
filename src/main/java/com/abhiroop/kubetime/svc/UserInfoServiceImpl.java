package com.abhiroop.kubetime.svc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.repo.UserRepo;


@Service
public class UserInfoServiceImpl implements IUserInfoService{

	@Autowired
	UserRepo usrRepository;
	
	@Override
	public User getUserById(long uuid) {
		return usrRepository.getById(uuid);
	}

	@Override
	public User deActivateUser(User usr) {
		usr.setStatus("I");
		usr=usrRepository.saveAndFlush(usr);
		return usr;
	}

	@Override
	public User getOneByEmail(String email) {
		User u =usrRepository.getByEmail(email);
		return u;
	}

}
