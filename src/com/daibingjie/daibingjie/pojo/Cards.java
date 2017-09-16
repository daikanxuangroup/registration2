package com.daibingjie.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 诊疗卡
 */

public class Cards implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cid;
	private String pname;
	private String sex;
	private String phone;
	private String idcard;
	private String pwd;
	private Double ramaining;
	private Integer doexist;
	private String by1;
	private Integer by2;
	private Set registrations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Cards() {
	}

	/** minimal constructor */
	public Cards(Integer cid, String pname, String sex, String phone, String idcard, String pwd, Integer doexist) {
		this.cid = cid;
		this.pname = pname;
		this.sex = sex;
		this.phone = phone;
		this.idcard = idcard;
		this.pwd = pwd;
		this.doexist = doexist;
	}

	/** full constructor */
	public Cards(Integer cid, String pname, String sex, String phone, String idcard, String pwd, Double ramaining,
			Integer doexist, String by1, Integer by2, Set registrations) {
		this.cid = cid;
		this.pname = pname;
		this.sex = sex;
		this.phone = phone;
		this.idcard = idcard;
		this.pwd = pwd;
		this.ramaining = ramaining;
		this.doexist = doexist;
		this.by1 = by1;
		this.by2 = by2;
		this.registrations = registrations;
	}

	// Property accessors

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Double getRamaining() {
		return this.ramaining;
	}

	public void setRamaining(Double ramaining) {
		this.ramaining = ramaining;
	}

	public Integer getDoexist() {
		return this.doexist;
	}

	public void setDoexist(Integer doexist) {
		this.doexist = doexist;
	}

	public String getBy1() {
		return this.by1;
	}

	public void setBy1(String by1) {
		this.by1 = by1;
	}

	public Integer getBy2() {
		return this.by2;
	}

	public void setBy2(Integer by2) {
		this.by2 = by2;
	}

	public Set getRegistrations() {
		return this.registrations;
	}

	public void setRegistrations(Set registrations) {
		this.registrations = registrations;
	}

}