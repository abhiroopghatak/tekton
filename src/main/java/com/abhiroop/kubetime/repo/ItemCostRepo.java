package com.abhiroop.kubetime.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abhiroop.kubetime.pojo.ItemCost;

@Transactional
@Repository
public interface ItemCostRepo extends JpaRepository<ItemCost, Long>{

	
	@Query("SELECT u FROM ItemCost u where u.clusterid = :clusterUniqueId")
	List<ItemCost> getAllPerCluster(long clusterUniqueId);
}
