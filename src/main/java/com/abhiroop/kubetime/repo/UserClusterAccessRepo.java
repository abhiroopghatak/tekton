package com.abhiroop.kubetime.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhiroop.kubetime.pojo.UserClusterAccess;


@Repository
public interface UserClusterAccessRepo extends JpaRepository<UserClusterAccess, Long>{

	
	@Query("SELECT uca FROM UserClusterAccess uca where uca.userUniqueId = :userUniqueId")
	List<UserClusterAccess> getAllPerUser(long userUniqueId);
}
