package com.transportsys.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "running_status")
public class RunningStatus {

	@Id
	private int RUN_ID;
	private String RUN_STATUS;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRC_ID")
	private TruckDTL truckdtl;
	
	public int getRUN_ID() {
		return RUN_ID;
	}
	public void setRUN_ID(int rUN_ID) {
		RUN_ID = rUN_ID;
	}
	public String getRUN_STATUS() {
		return RUN_STATUS;
	}
	public void setRUN_STATUS(String rUN_STATUS) {
		RUN_STATUS = rUN_STATUS;
	}
	public TruckDTL getTruckdtl() {
		return truckdtl;
	}
	public void setTruckdtl(TruckDTL truckdtl) {
		this.truckdtl = truckdtl;
	}
	@Override
	public String toString() {
		return "RunningStatus [RUN_ID=" + RUN_ID + ", RUN_STATUS=" + RUN_STATUS + ", truckdtl=" + truckdtl + "]";
	}
	
	

}
