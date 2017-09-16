package com.daibingjie.pojo;

/**
 * 网上预约单表
 */

public class Reservation implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer red;
	private Patients patients;
	private Bookable bookable;
	private Integer state;
	private String by1;
	private Integer by2;

	// Constructors

	/** default constructor */
	public Reservation() {
	}

	/** minimal constructor */
	public Reservation(Integer red) {
		this.red = red;
	}

	/** full constructor */
	public Reservation(Integer red, Patients patients, Bookable bookable, Integer state, String by1, Integer by2) {
		this.red = red;
		this.patients = patients;
		this.bookable = bookable;
		this.state = state;
		this.by1 = by1;
		this.by2 = by2;
	}

	// Property accessors

	public Integer getRed() {
		return this.red;
	}

	public void setRed(Integer red) {
		this.red = red;
	}

	public Patients getPatients() {
		return this.patients;
	}

	public void setPatients(Patients patients) {
		this.patients = patients;
	}

	public Bookable getBookable() {
		return this.bookable;
	}

	public void setBookable(Bookable bookable) {
		this.bookable = bookable;
	}

	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
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