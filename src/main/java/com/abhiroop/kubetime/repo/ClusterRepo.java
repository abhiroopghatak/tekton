package com.abhiroop.kubetime.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhiroop.kubetime.pojo.Cluster;

public interface ClusterRepo extends JpaRepository<Cluster, Long>{

	
}
