package com.transportsys.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "branch_dtl")
public class BranchDTL {
	@Id
	private int BRN_ID;
	private String BRN_NAME;
	private String BRN_ADDRESS;
	private String BRN_CONTACT;
	private String BRN_HEAD_NAME;
	
	/*@JsonManagedReference*/
	@OneToMany(mappedBy="branchdtl")
	private Set<ManagerDTL> managerDtl;
	
	
	
	
	public BranchDTL(int bRN_ID, String bRN_NAME, String bRN_ADDRESS, String bRN_CONTACT, String bRN_HEAD_NAME,
			Set<ManagerDTL> managerDtl) {
		super();
		BRN_ID = bRN_ID;
		BRN_NAME = bRN_NAME;
		BRN_ADDRESS = bRN_ADDRESS;
		BRN_CONTACT = bRN_CONTACT;
		BRN_HEAD_NAME = bRN_HEAD_NAME;
		this.managerDtl = managerDtl;
	}
	public Set<ManagerDTL> getManagerDtl() {
		return managerDtl;
	}
	public void setManagerDtl(Set<ManagerDTL> managerDtl) {
		this.managerDtl = managerDtl;
	}
	public BranchDTL() {
		
	}
	public BranchDTL(int bRN_ID, String bRN_NAME, String bRN_ADDRESS, String bRN_CONTACT, String bRN_HEAD_NAME) {
		super();
		BRN_ID = bRN_ID;
		BRN_NAME = bRN_NAME;
		BRN_ADDRESS = bRN_ADDRESS;
		BRN_CONTACT = bRN_CONTACT;
		BRN_HEAD_NAME = bRN_HEAD_NAME;
	}
	public int getBRN_ID() {
		return BRN_ID;
	}
	public void setBRN_ID(int bRN_ID) {
		BRN_ID = bRN_ID;
	}
	public String getBRN_NAME() {
		return BRN_NAME;
	}
	public void setBRN_NAME(String bRN_NAME) {
		BRN_NAME = bRN_NAME;
	}
	public String getBRN_ADDRESS() {
		return BRN_ADDRESS;
	}
	public void setBRN_ADDRESS(String bRN_ADDRESS) {
		BRN_ADDRESS = bRN_ADDRESS;
	}
	public String getBRN_CONTACT() {
		return BRN_CONTACT;
	}
	public void setBRN_CONTACT(String bRN_CONTACT) {
		BRN_CONTACT = bRN_CONTACT;
	}
	public String getBRN_HEAD_NAME() {
		return BRN_HEAD_NAME;
	}
	public void setBRN_HEAD_NAME(String bRN_HEAD_NAME) {
		BRN_HEAD_NAME = bRN_HEAD_NAME;
	}
	@Override
	public String toString() {
		return "BranchDTL [BRN_ID=" + BRN_ID + ", BRN_NAME=" + BRN_NAME + ", BRN_ADDRESS=" + BRN_ADDRESS
				+ ", BRN_CONTACT=" + BRN_CONTACT + ", BRN_HEAD_NAME=" + BRN_HEAD_NAME + "]";
	}
	
	

}
