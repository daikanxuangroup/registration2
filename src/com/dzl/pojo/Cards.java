package com.dzl.pojo;

import java.io.Serializable;
import java.util.Date;

public class Cards implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String pname;
	private String sex;
	private String phone;
	private String idcard;
	private String pwd;
	private Date newdate;
	private Double remaining;
	private Integer doexist;
	private String password2;
	
	
	
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Integer getDoexist() {
		return doexist;
	}
	public void setDoexist(Integer doexist) {
		this.doexist = doexist;
	}
	
	
	public Date getNewdate() {
		return newdate;
	}
	public void setNewdate(Date newdate) {
		this.newdate = newdate;
	}
	public Double getRemaining() {
		return remaining;
	}
	public void setRemaining(Double remaining) {
		this.remaining = remaining;
	}
	public Cards() {
		super();
	}
	public Cards(Integer cid, String pname, String sex, String phone, String idcard, String pwd, Date newdate,
			Double remaining, Integer doexist) {
		super();
		this.cid = cid;
		this.pname = pname;
		this.sex = sex;
		this.phone = phone;
		this.idcard = idcard;
		this.pwd = pwd;
		this.newdate = newdate;
		this.remaining = remaining;
		this.doexist = doexist;
	}
	@Override
	public String toString() {
		return "Cards [cid=" + cid + ", pname=" + pname + ", sex=" + sex + ", phone=" + phone + ", idcard=" + idcard
				+ ", pwd=" + pwd + ", newdate=" + newdate + ", remaining=" + remaining + ", doexist=" + doexist + "]";
	}
	
	
	
}
