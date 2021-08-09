package com.abhiroop.kubetime.svc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.pojo.ItemCost;
import com.abhiroop.kubetime.repo.ItemCostRepo;
import com.abhiroop.kubetime.svc.ICostService;

@Service
public class CostServiceImpl implements ICostService {

	@Autowired
	ItemCostRepo costrepo;

	@Override
	public ItemCost getCostDeatilPerCluster(long clusterId) {
		System.out.println("getCostDeatilPerCluster : service call invoked");
		return costrepo.getAllPerCluster(clusterId);
	}

	@Override
	public ItemCost addCostDetail(ItemCost costItem) {
		costItem = costrepo.saveAndFlush(costItem);
		return costItem;
	}

	@Override
	public ItemCost upadteCostDetail(ItemCost costItem) {
		return costItem = costrepo.saveAndFlush(costItem);
	}

}
