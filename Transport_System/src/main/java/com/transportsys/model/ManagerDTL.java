package com.transportsys.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "manager_dtl")
public class ManagerDTL {

	@Id	
	/*@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="TAB_CUSTOMER_SEQ")*/
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MySequence")
	@SequenceGenerator(name="MySequence", sequenceName="SEQUENCE1", allocationSize=1)
	private int MGR_ID;
	private String MGR_USER_ID;
	private String MGR_PASSWORD;
	private String MGR_NAME;
	private String MGR_ID_PROOF;

	/*@Temporal(TemporalType.DATE)*/
	/*@DateTimeFormat(pattern="YYYY-MM-DD")*/
	private Date MGR_DOB;
	/*@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")*/
	private Date MGR_JOIN_DT;
	private Float MGR_SALARY;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "BRN_ID")
	private BranchDTL branchdtl;
	
	public ManagerDTL() {

	}
	public ManagerDTL(int mGR_ID, String mGR_USER_ID, String mGR_PASSWORD, String mGR_NAME, String mGR_ID_PROOF,
			Date mGR_DOB, Date mGR_JOIN_DT,BranchDTL branchdtl) {
		super();
		MGR_ID = mGR_ID;
		MGR_USER_ID = mGR_USER_ID;
		MGR_PASSWORD = mGR_PASSWORD;
		MGR_NAME = mGR_NAME;
		MGR_ID_PROOF = mGR_ID_PROOF;
		MGR_DOB = mGR_DOB;
		MGR_JOIN_DT = mGR_JOIN_DT;
		this.branchdtl = branchdtl;
	}
	
	public int getMGR_ID() {
		return MGR_ID;
	}

	public void setMGR_ID(int mGR_ID) {
		MGR_ID = mGR_ID;
	}

	public String getMGR_USER_ID() {
		return MGR_USER_ID;
	}

	public void setMGR_USER_ID(String mGR_USER_ID) {
		MGR_USER_ID = mGR_USER_ID;
	}

	public String getMGR_PASSWORD() {
		return MGR_PASSWORD;
	}

	public void setMGR_PASSWORD(String mGR_PASSWORD) {
		MGR_PASSWORD = mGR_PASSWORD;
	}

	public String getMGR_NAME() {
		return MGR_NAME;
	}

	public void setMGR_NAME(String mGR_NAME) {
		MGR_NAME = mGR_NAME;
	}

	public String getMGR_ID_PROOF() {
		return MGR_ID_PROOF;
	}

	public void setMGR_ID_PROOF(String mGR_ID_PROOF) {
		MGR_ID_PROOF = mGR_ID_PROOF;
	}

	public Date getMGR_DOB() {
		return MGR_DOB;
	}

	public void setMGR_DOB(Date mGR_DOB) {
		MGR_DOB = mGR_DOB;
	}

	public Date getMGR_JOIN_DT() {
		return MGR_JOIN_DT;
	}

	public void setMGR_JOIN_DT(Date mGR_JOIN_DT) {
		MGR_JOIN_DT = mGR_JOIN_DT;
	}

	public BranchDTL getBranchdtl() {
		return branchdtl;
	}

	public void setBranchdtl(BranchDTL branchdtl) {
		this.branchdtl = branchdtl;
	}
	
	public Float getMGR_SALARY() {
		return MGR_SALARY;
	}

	public void setMGR_SALARY(Float mGR_SALARY) {
		MGR_SALARY = mGR_SALARY;
	}
	@Override
	public String toString() {
		return "ManagerDTL [MGR_ID=" + MGR_ID + ", MGR_USER_ID=" + MGR_USER_ID + ", MGR_PASSWORD=" + MGR_PASSWORD
				+ ", MGR_NAME=" + MGR_NAME + ", MGR_ID_PROOF=" + MGR_ID_PROOF + ", MGR_DOB=" + MGR_DOB
				+ ", MGR_JOIN_DT=" + MGR_JOIN_DT + ", MGR_SALARY=" + MGR_SALARY + ", branchdtl=" + branchdtl + "]";
	}

}
