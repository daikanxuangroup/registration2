package com.dzl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Bookable implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer bid;
	private Integer doid;
	private Date bdate;
	private Integer xcum;
	private Integer xcyum;
	private Double bcost;
	private String doname;
	private String dename;
	private Integer snum;
	private String pname;
	private Integer medcard;
	
	
	
	
	
	public Integer getMedcard() {
		return medcard;
	}
	public void setMedcard(Integer medcard) {
		this.medcard = medcard;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getSnum() {
		return snum;
	}
	public void setSnum(Integer snum) {
		this.snum = snum;
	}
	public String getDoname() {
		return doname;
	}
	public void setDoname(String doname) {
		this.doname = doname;
	}
	public String getDename() {
		return dename;
	}
	public void setDename(String dename) {
		this.dename = dename;
	}
	
	public Double getBcost() {
		return bcost;
	}
	public void setBcost(Double bcost) {
		this.bcost = bcost;
	}
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getDoid() {
		return doid;
	}
	public void setDoid(Integer doid) {
		this.doid = doid;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public Integer getXcum() {
		return xcum;
	}
	public void setXcum(Integer xcum) {
		this.xcum = xcum;
	}
	public Integer getXcyum() {
		return xcyum;
	}
	public void setXcyum(Integer xcyum) {
		this.xcyum = xcyum;
	}
	public Bookable() {
		super();
	}
	
	
}
