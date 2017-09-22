package com.dzl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Books implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer red;
	private String pname;
	private String idcard;
	private Integer medcard;
	private String dename;
	private String doname;
	private Date bdate;
	private Double bcost;
	private String phone;
	private Integer starttime;
	private Integer snum;
	private Integer bid;
	
	
	
	
	
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public Integer getSnum() {
		return snum;
	}
	public void setSnum(Integer snum) {
		this.snum = snum;
	}
	public Integer getStarttime() {
		return starttime;
	}
	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRed() {
		return red;
	}
	public void setRed(Integer red) {
		this.red = red;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Integer getMedcard() {
		return medcard;
	}
	public void setMedcard(Integer medcard) {
		this.medcard = medcard;
	}
	public String getDename() {
		return dename;
	}
	public void setDename(String dename) {
		this.dename = dename;
	}
	public String getDoname() {
		return doname;
	}
	public void setDoname(String doname) {
		this.doname = doname;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public Double getBcost() {
		return bcost;
	}
	public void setBcost(Double bcost) {
		this.bcost = bcost;
	}
	
	
	@Override
	public String toString() {
		return "Books [red=" + red + ", pname=" + pname + ", idcard=" + idcard + ", medcard=" + medcard + ", dename="
				+ dename + ", doname=" + doname + ", bdate=" + bdate + ", bcost=" + bcost + ", phone=" + phone
				+ ", starttime=" + starttime + "]";
	}
	public Books() {
		super();
	}
	
	
}
