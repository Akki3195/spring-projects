package com.transportsys.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportsys.dao.ManagerDaoImpl;
import com.transportsys.model.ManagerDTL;
import com.transportsys.model.RunningStatus;
import com.transportsys.model.TruckDTL;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	ManagerDaoImpl mgrDao;
	
	public ManagerDTL managerLogin(String uId, String uPass) {
		ManagerDTL mgrObj = mgrDao.managerLogin(uId, uPass);
			return mgrObj;
	}

	@Override
	public Map< String, TruckDTL> routeDetails(int brnId) {
		TruckDTL trcObj = null;
		RunningStatus runnStsObj = null;
		Map< String, TruckDTL> routeData = new HashMap<>();
		
		List<Object[]> objList = mgrDao.routeDetails(brnId);
		for (Object[] o : objList) {
			for (Object obj : o) {
				if (obj instanceof TruckDTL) {
					trcObj = (TruckDTL) obj;
				} else if (obj instanceof RunningStatus) {
					runnStsObj = (RunningStatus) obj;
				}
			}
			routeData.put(runnStsObj.getRUN_STATUS(), trcObj);
		}
		return routeData;
	}

	@Override
	public boolean updateStatus(int truckId, String runningSts) {
		return mgrDao.updateStatus(truckId, runningSts);
		
	}
	
	@Override
	public List<TruckDTL> viewIstalTruck(Date frmDt, Date toDt){
		List<TruckDTL> truckDtlList = mgrDao.viewIstalTruck(frmDt, toDt);
		return truckDtlList;
	}
	
	@Override
	public List<TruckDTL> getTruckList(String from , String to){
		return mgrDao.getTruckList(from, to);
	}

	@Override
	public List<TruckDTL> getTruckListByPage(int pageId,int totalRecords,TruckDTL truckDtl) {
		
		return mgrDao.getTruckListByPage(pageId, totalRecords,truckDtl);
	}
	
	public int getTotalNoOfRecords(TruckDTL truckDtl) {
		return mgrDao.getTotalNoOfRecords(truckDtl);
		
	}
	
	
}
