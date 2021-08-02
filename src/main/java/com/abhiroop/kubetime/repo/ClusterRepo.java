package com.abhiroop.kubetime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abhiroop.kubetime.pojo.Cluster;


@Transactional
@Repository
public interface ClusterRepo extends JpaRepository<Cluster, Long>{

	
}
