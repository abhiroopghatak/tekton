package com.abhiroop.kubetime.svc.impl;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.config.SystemConstants;
import com.abhiroop.kubetime.pojo.User;
import com.abhiroop.kubetime.repo.UserRepo;
import com.abhiroop.kubetime.svc.IUserInfoService;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	UserRepo usrRepository;

	@Autowired
	private PasswordEncoder p;

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
	public User signUpUser(User user, boolean isUpdate) throws Exception {
		User userWithDuplicateUsername = getOneByEmail(user.getEmail());
		if (!isUpdate) {
			if (userWithDuplicateUsername != null) {

				System.err.print(" User with email " + user.getEmail() + " already exists in system. ");
				throw new RuntimeException("Email already exists");
			} else {
				if (StringUtils.isEmpty(user.getRole()))
					user.setRole(SystemConstants.END_USER_ROLE);
				if (StringUtils.isEmpty(user.getStatus()))
					user.setStatus(SystemConstants.RequestedStatus);
				user.setCreateTime(new Date());
				String encryptedPassword = p.encode(user.getPwd());
				user.setPwd(encryptedPassword);

				user = usrRepository.save(user);
			}
		}

		return user;
	}

	///////// helper functions///////////////////
	// Returns base64 encoded salt
	private String getNewSalt() throws Exception {
		// Don't use Random!
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		// NIST recommends minimum 4 bytes. We use 8.
		byte[] salt = new byte[8];
		random.nextBytes(salt);
		return Base64.getEncoder().encodeToString(salt);
	}

	private String getEncryptedPassword(String password, String salt) throws Exception {
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160; // for SHA1
		int iterations = 20000; // NIST specifies 10000

		byte[] saltBytes = Base64.getDecoder().decode(salt);
		KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

		byte[] encBytes = f.generateSecret(spec).getEncoded();
		return Base64.getEncoder().encodeToString(encBytes);
	}

	private boolean authenticateUser(User user) throws Exception {
		User userFromDb = getOneByEmail(user.getEmail());
		if (userFromDb == null) {
			return false;
		} else {
			String salt = "DUMMY";
			String calculatedHash = getEncryptedPassword(user.getPwd(), salt);
			if (calculatedHash.equals(userFromDb.getPwd())) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = getOneByEmail(username);
		org.springframework.security.core.userdetails.User springUser = null;
		if (u != null) {
			springUser = new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPwd(),
					new ArrayList<>());
		}
		return springUser;
	}
}
