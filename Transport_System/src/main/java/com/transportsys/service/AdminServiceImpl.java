package com.transportsys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transportsys.dao.AdminDao;
import com.transportsys.model.AdminDTL;
import com.transportsys.model.BranchDTL;
import com.transportsys.model.ManagerDTL;
import com.transportsys.model.TruckDTL;

/**
 * @author akki
 *
 */
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminDao ad;

	@Override
	public List<AdminDTL> getAdminDTL() {
		
		return null;
	}

	@Override
	public boolean checkUser(String uId, String uPass) {
		if (ad.checkUser(uId, uPass))
			return true;
		else
			return false;
	}

	@Override
	public boolean saveManagerDtl(ManagerDTL managerDtl) {
		
		return ad.saveManagerDtl(managerDtl);
	}

	@Override
	public List<BranchDTL> GetBranchDtl() {
		return ad.getAllBranchDtl();
	}

	@Override
	public boolean saveTruckDtl(TruckDTL truckDtl) {
		return ad.saveTruckDtl(truckDtl);
	}

	@Override
	public List<ManagerDTL> getManagerDtl(String letter,String urlParam) {
		return ad.getManagerDtlByName(letter,urlParam);
	}

	@Override
	public boolean deleteManager(int mgrId) {
		if(ad.deleteManager(mgrId)) {
			return true;
		}
		
		return false;
	}

	@Override
	public List<TruckDTL> getTruckDtl(String letter,String check) {
		return ad.getTruckDtl(letter, check);
	}

	
	
}
