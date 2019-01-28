package com.transportsys.dao;

import java.util.Date;
import java.util.List;

import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;

public interface ManagerDao {
	
	ManagerDTL managerLogin(String username , String password);
	List<Object[]> routeDetails(int brnId);
	boolean updateStatus(int truckId , String runningSts);
	
	List<TruckDTL> viewIstalTruck(Date frmDt , Date toDt);
	
	List<TruckDTL> getTruckList(String from , String to);
	
	public List<TruckDTL> getTruckListByPage(int pageId,int totalRecords,TruckDTL truckDtl);

	public int getTotalNoOfRecords(TruckDTL truckDtl);
}
