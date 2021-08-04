package com.abhiroop.kubetime.svc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhiroop.kubetime.pojo.ItemCost;
import com.abhiroop.kubetime.repo.ItemCostRepo;

@Service
public class CostServiceImpl implements ICostService {

	@Autowired
	ItemCostRepo costrepo;

	@Override
	public List<ItemCost> getCostDeatilPerCluster(long clusterId) {

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
