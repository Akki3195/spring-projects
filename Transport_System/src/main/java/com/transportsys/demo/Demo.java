package com.transportsys.demo;

import org.springframework.core.SpringVersion;
	
public class Demo {
public static void main(String[] args) {
	/*System.out.println("version: " + SpringVersion.getVersion());
	BranchDTL br = new BranchDTL(bRN_ID, bRN_NAME, bRN_ADDRESS, bRN_CONTACT, bRN_HEAD_NAME)
	ManagerDTL mg = new ManagerDTL(12, "sdf", "asdfsdf", "sdf sf", "34243454", "10/10/2018", "10/10/2018", branchdtl)*/
	System.out.println("hibernate version:"+ org.hibernate.annotations.common.Version.getVersionString());
	System.out.println("Spring version:"+ SpringVersion.getVersion());
	
	/*AdminDaoImpl adi = new AdminDaoImpl();
	List<BranchDTL> l = adi.getAllBranchDtl();
	System.out.println(l);
	for(BranchDTL obj :l) {
		System.out.println(obj.getBRN_ADDRESS() + " "+obj.getBRN_ID());
	}*/
	
	/*AdminDaoImpl adimpl = new AdminDaoImpl();
	System.out.println();*/
	
}
}
