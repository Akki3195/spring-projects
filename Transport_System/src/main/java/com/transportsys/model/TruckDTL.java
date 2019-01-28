package com.transportsys.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "truck_dtl")
public class TruckDTL {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MySequence1")
	@SequenceGenerator(name="MySequence1", sequenceName="SEQUENCE1", allocationSize=1)
	private int TRC_ID;
	private String TRC_NO;
	private String TRC_MODEL;
	private String TRC_INSR_YN;
	private String TRC_INSR_COMP_NAME;
	private String TRC_OWNER;
	private String TRC_CONTACT;
	private String TRC_ROUTE_FROM;
	private String TRC_ROUTE_TO;
	private Date TRC_INSTL_DT;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "BRN_ID")
	private BranchDTL branchdtl;
	public int getTRC_ID() {
		return TRC_ID;
	}
	public void setTRC_ID(int tRC_ID) {
		TRC_ID = tRC_ID;
	}
	public String getTRC_NO() {
		return TRC_NO;
	}
	public void setTRC_NO(String tRC_NO) {
		TRC_NO = tRC_NO;
	}
	public String getTRC_MODEL() {
		return TRC_MODEL;
	}
	public void setTRC_MODEL(String tRC_MODEL) {
		TRC_MODEL = tRC_MODEL;
	}
	public String getTRC_INSR_YN() {
		return TRC_INSR_YN;
	}
	public void setTRC_INSR_YN(String tRC_INSR_YN) {
		TRC_INSR_YN = tRC_INSR_YN;
	}
	public String getTRC_INSR_COMP_NAME() {
		return TRC_INSR_COMP_NAME;
	}
	public void setTRC_INSR_COMP_NAME(String tRC_INSR_COMP_NAME) {
		TRC_INSR_COMP_NAME = tRC_INSR_COMP_NAME;
	}
	public String getTRC_OWNER() {
		return TRC_OWNER;
	}
	public void setTRC_OWNER(String tRC_OWNER) {
		TRC_OWNER = tRC_OWNER;
	}
	public String getTRC_CONTACT() {
		return TRC_CONTACT;
	}
	public void setTRC_CONTACT(String tRC_CONTACT) {
		TRC_CONTACT = tRC_CONTACT;
	}
	public String getTRC_ROUTE_FROM() {
		return TRC_ROUTE_FROM;
	}
	public void setTRC_ROUTE_FROM(String tRC_ROUTE_FROM) {
		TRC_ROUTE_FROM = tRC_ROUTE_FROM;
	}
	public String getTRC_ROUTE_TO() {
		return TRC_ROUTE_TO;
	}
	public void setTRC_ROUTE_TO(String tRC_ROUTE_TO) {
		TRC_ROUTE_TO = tRC_ROUTE_TO;
	}
	public Date getTRC_INSTL_DT() {
		return TRC_INSTL_DT;
	}
	public void setTRC_INSTL_DT(Date tRC_INSTL_DT) {
		TRC_INSTL_DT = tRC_INSTL_DT;
	}
	public BranchDTL getBranchdtl() {
		return branchdtl;
	}
	public void setBranchdtl(BranchDTL branchdtl) {
		this.branchdtl = branchdtl;
	}
	@Override
	public String toString() {
		return "TruckDTL [TRC_ID=" + TRC_ID + ", TRC_NO=" + TRC_NO + ", TRC_MODEL=" + TRC_MODEL + ", TRC_INSR_YN="
				+ TRC_INSR_YN + ", TRC_INSR_COMP_NAME=" + TRC_INSR_COMP_NAME + ", TRC_OWNER=" + TRC_OWNER
				+ ", TRC_CONTACT=" + TRC_CONTACT + ", TRC_ROUTE_FROM=" + TRC_ROUTE_FROM + ", TRC_ROUTE_TO="
				+ TRC_ROUTE_TO + ", TRC_INSTL_DT=" + TRC_INSTL_DT + ", branchdtl=" + branchdtl + "]";
	}
	
	
}
