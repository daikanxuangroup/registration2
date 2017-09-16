package com.daibingjie.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 可预约排班表
 */

public class Bookable implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bid;
	private Doctors doctors;

	private Date bdate;
	private Integer starttime;
	private Integer used;
	private Integer bnum;
	private Integer ynum;
	private Integer xcum;
	private Integer xcyum;
	private String by1;
	private Integer by2;
	private Set registrations = new HashSet(0);
	private Set reservations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Bookable() {
	}

	/** minimal constructor */
	public Bookable(Integer bid, Date bdate, Integer starttime, Integer used, Integer bnum, Integer ynum, Integer xcum,
			Integer xcyum) {
		this.bid = bid;
		this.bdate = bdate;
		this.starttime = starttime;
		this.used = used;
		this.bnum = bnum;
		this.ynum = ynum;
		this.xcum = xcum;
		this.xcyum = xcyum;
	}

	/** full constructor */
	public Bookable(Integer bid, Doctors doctors, Date bdate, Integer starttime, Integer used, Integer bnum, Integer ynum,
			Integer xcum, Integer xcyum, String by1, Integer by2, Set registrations, Set reservations) {
		this.bid = bid;
		this.doctors = doctors;
		this.bdate = bdate;
		this.starttime = starttime;
		this.used = used;
		this.bnum = bnum;
		this.ynum = ynum;
		this.xcum = xcum;
		this.xcyum = xcyum;
		this.by1 = by1;
		this.by2 = by2;
		this.registrations = registrations;
		this.reservations = reservations;
	}

	// Property accessors

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public Doctors getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Doctors doctors) {
		this.doctors = doctors;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Integer getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Integer starttime) {
		this.starttime = starttime;
	}

	public Integer getUsed() {
		return this.used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getBnum() {
		return this.bnum;
	}

	public void setBnum(Integer bnum) {
		this.bnum = bnum;
	}

	public Integer getYnum() {
		return this.ynum;
	}

	public void setYnum(Integer ynum) {
		this.ynum = ynum;
	}

	public Integer getXcum() {
		return this.xcum;
	}

	public void setXcum(Integer xcum) {
		this.xcum = xcum;
	}

	public Integer getXcyum() {
		return this.xcyum;
	}

	public void setXcyum(Integer xcyum) {
		this.xcyum = xcyum;
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

	public Set getReservations() {
		return this.reservations;
	}

	public void setReservations(Set reservations) {
		this.reservations = reservations;
	}

}