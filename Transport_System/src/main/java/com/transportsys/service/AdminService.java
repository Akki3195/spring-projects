package com.transportsys.service;

import java.util.List;

import com.transportsys.model.AdminDTL;
import com.transportsys.model.BranchDTL;
import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;

public interface AdminService {
	
	public  List<AdminDTL> getAdminDTL();
	
	public boolean checkUser(String uId, String uPass);
	
	public boolean saveManagerDtl(ManagerDTL managerDtl);
	
	public boolean saveTruckDtl(TruckDTL truckDtl);
	
	public List<BranchDTL> GetBranchDtl();
	
	public List<ManagerDTL> getManagerDtl(String letter,String urlParam);
	
	public boolean deleteManager(int mgrId);
	
	public List<TruckDTL> getTruckDtl(String letter,String check);
	
	
	
	
	
	
	
	
}
