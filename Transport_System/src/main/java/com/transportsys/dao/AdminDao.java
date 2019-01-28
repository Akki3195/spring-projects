package com.transportsys.dao;

import java.util.List;

import com.transportsys.model.BranchDTL;
import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;

public interface AdminDao {
	
	
	boolean checkUser(String uId, String uPass);
	
	boolean saveManagerDtl(ManagerDTL managerDtl);
	
	List<BranchDTL> getAllBranchDtl();

	boolean saveTruckDtl(TruckDTL truckDtl);
	
	List<ManagerDTL> getManagerDtlByName(String letter,String urlParam);
	
	boolean deleteManager(int mgrId);
	
	List<TruckDTL> getTruckDtl(String letter,String check);
	
	
	

}
