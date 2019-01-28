package com.transportsys.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;

public interface ManagerService {
	
	public ManagerDTL managerLogin(String uId, String uPass);
	
	public Map< String, TruckDTL> routeDetails(int brnId);
	
	public boolean updateStatus(int truckId , String runningSts);
	
	public List<TruckDTL> viewIstalTruck(Date frmDt, Date toDt);
	
	public List<TruckDTL> getTruckList(String from , String to);
	
	public List<TruckDTL> getTruckListByPage(int pageId,int totalRecords,TruckDTL truckDtl);
	
	public int getTotalNoOfRecords(TruckDTL truckDtl);
	

}
