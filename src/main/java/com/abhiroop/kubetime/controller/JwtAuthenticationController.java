package com.abhiroop.kubetime.controller;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abhiroop.kubetime.config.JwtTokenUtil;
import com.abhiroop.kubetime.config.SystemConstants;
import com.abhiroop.kubetime.pojo.JwtRequest;
import com.abhiroop.kubetime.pojo.JwtResponse;
import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.svc.IUserInfoService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private IUserInfoService userInfoService;

	@PostMapping(value = "/api/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		User u = userInfoService.getOneByEmail(authenticationRequest.getUsername());
		org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(
				u.getEmail(), u.getPwd(), new ArrayList<>());
		final UserDetails userDetails = springUser;

		if (StringUtils.equals(SystemConstants.StatusActive, u.getStatus()) && userDetails != null) {
			final String token = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new JwtResponse(token, u.getEmail(), u.getRole()));
		} else {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setLocation(null);
			responseHeaders.set("STATUS", "USERNOTFOUND");

			return new ResponseEntity<>(null, responseHeaders, HttpStatus.NOT_FOUND);
		}
	}

	private void authenticate(String loginid, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginid, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
