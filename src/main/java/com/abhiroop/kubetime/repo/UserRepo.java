package com.abhiroop.kubetime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abhiroop.kubetime.pojo.User;


@Transactional
@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	
	@Query("SELECT u FROM User u where u.email = :email")
	User getByEmail(String email);

}
