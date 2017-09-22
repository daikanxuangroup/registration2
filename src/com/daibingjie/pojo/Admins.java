package com.daibingjie.pojo;

import java.util.Date;

/**
 * 管理员表
 */

public class Admins implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer aid;
	private Doctors doctors;
	private String aname;
	private String pwd;
	private Integer state;
	private Integer aexist;
	private String by1;
	private Integer by2;
	private Integer doid;
	private Date times;
	
	// Constructors

	/** default constructor */
	public Admins() {
	}

	
	public Admins(Integer aid, Doctors doctors, String aname, String pwd, Integer state, Integer aexist, String by1,
			Integer by2, Integer doid, Date times) {
		super();
		this.aid = aid;
		this.doctors = doctors;
		this.aname = aname;
		this.pwd = pwd;
		this.state = state;
		this.aexist = aexist;
		this.by1 = by1;
		this.by2 = by2;
		this.doid = doid;
		this.times = times;
	}





	public Date getTimes() {
		return times;
	}





	public void setTimes(Date times) {
		this.times = times;
	}





	public Integer getDoid() {
		return doid;
	}



	public void setDoid(Integer doid) {
		this.doid = doid;
	}



	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Doctors getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Doctors doctors) {
		this.doctors = doctors;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getPwd() {
		return this.pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getAexist() {
		return this.aexist;
	}

	public void setAexist(Integer aexist) {
		this.aexist = aexist;
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

}