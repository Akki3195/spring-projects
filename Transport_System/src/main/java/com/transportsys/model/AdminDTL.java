package com.transportsys.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "ADMIN_DTL")
public class AdminDTL {
	
	private int AD_ID;
	private String AD_NAME;	
	private String AD_PASSWORD;
	private String AD_USER_ID;
	
	@Id
	public int getAD_ID() {
		return AD_ID;
	}
	public void setAD_ID(int aD_ID) {
		AD_ID = aD_ID;
	}
	public String getAD_NAME() {
		return AD_NAME;
	}
	public void setAD_NAME(String aD_NAME) {
		AD_NAME = aD_NAME;
	}
	public String getAD_PASSWORD() {
		return AD_PASSWORD;
	}
	public void setAD_PASSWORD(String aD_PASSWORD) {
		AD_PASSWORD = aD_PASSWORD;
	}
	public String getAD_USER_ID() {
		return AD_USER_ID;
	}
	public void setAD_USER_ID(String aD_USER_ID) {
		AD_USER_ID = aD_USER_ID;
	}
	@Override
	public String toString() {
		return "AdminDTL [AD_ID=" + AD_ID + ", AD_NAME=" + AD_NAME + ", AD_PASSWORD=" + AD_PASSWORD + ", AD_USER_ID="
				+ AD_USER_ID + "]";
	}
	
	
	

}
