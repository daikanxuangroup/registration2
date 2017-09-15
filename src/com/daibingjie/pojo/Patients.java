package com.daibingjie.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 网上预约病人表 
 */

public class Patients implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer pid;
	private String pname;
	private String sex;
	private String phone;
	private String idcard;
	private String pwd;
	private String medcard;
	private String email;
	private String by1;
	private Integer by2;
	private Set prescriptons = new HashSet(0);
	private Set reservations = new HashSet(0);
	private Set histories = new HashSet(0);

	// Constructors

	/** default constructor */
	public Patients() {
	}

	/** minimal constructor */
	public Patients(Integer pid, String pname, String sex, String phone, String idcard, String pwd) {
		this.pid = pid;
		this.pname = pname;
		this.sex = sex;
		this.phone = phone;
		this.idcard = idcard;
		this.pwd = pwd;
	}

	/** full constructor */
	public Patients(Integer pid, String pname, String sex, String phone, String idcard, String pwd, String medcard,
			String email, String by1, Integer by2, Set prescriptons, Set reservations, Set histories) {
		this.pid = pid;
		this.pname = pname;
		this.sex = sex;
		this.phone = phone;
		this.idcard = idcard;
		this.pwd = pwd;
		this.medcard = medcard;
		this.email = email;
		this.by1 = by1;
		this.by2 = by2;
		this.prescriptons = prescriptons;
		this.reservations = reservations;
		this.histories = histories;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public String getMedcard() {
		return this.medcard;
	}

	public void setMedcard(String medcard) {
		this.medcard = medcard;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set getPrescriptons() {
		return this.prescriptons;
	}

	public void setPrescriptons(Set prescriptons) {
		this.prescriptons = prescriptons;
	}

	public Set getReservations() {
		return this.reservations;
	}

	public void setReservations(Set reservations) {
		this.reservations = reservations;
	}

	public Set getHistories() {
		return this.histories;
	}

	public void setHistories(Set histories) {
		this.histories = histories;
	}

}