package com.abhiroop.kubetime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.svc.IUserInfoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserDataController {

	
	@Autowired
	private IUserInfoService userInfoService;
	
	@GetMapping("/getOne/{email}")
	public User getByEmail(@PathVariable String email) {
		
		
		return userInfoService.getOneByEmail(email);
	}
}
