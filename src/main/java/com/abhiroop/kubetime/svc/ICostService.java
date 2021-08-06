package com.abhiroop.kubetime.svc;

import java.util.List;

import com.abhiroop.kubetime.pojo.ItemCost;

public interface ICostService {

	
	
	ItemCost addCostDetail(ItemCost costItem);
	ItemCost upadteCostDetail(ItemCost costItem);
	ItemCost getCostDeatilPerCluster(long clusterId);
}
