package com.daibingjie.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 药品表
 */

public class Drug implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer dyid;
	private Integer drid;
	private Drugtype drugtype;
	private String drname;
	private Integer drsum;
	private Double drprice;
	private Integer drstate;
	private String by1;
	private Integer by2;
	private Set drugandprescriptons = new HashSet(0);
	private Set druganddepartses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Drug() {
	}



	public Drug(Integer dyid, Integer drid, Drugtype drugtype, String drname, Integer drsum, Double drprice,
			Integer drstate, String by1, Integer by2, Set drugandprescriptons, Set druganddepartses) {
		super();
		this.dyid = dyid;
		this.drid = drid;
		this.drugtype = drugtype;
		this.drname = drname;
		this.drsum = drsum;
		this.drprice = drprice;
		this.drstate = drstate;
		this.by1 = by1;
		this.by2 = by2;
		this.drugandprescriptons = drugandprescriptons;
		this.druganddepartses = druganddepartses;
	}



	// Property accessors

	public Integer getDyid() {
		return dyid;
	}

	public void setDyid(Integer dyid) {
		this.dyid = dyid;
	}

	public Integer getDrid() {
		return this.drid;
	}

	public void setDrid(Integer drid) {
		this.drid = drid;
	}

	public Drugtype getDrugtype() {
		return this.drugtype;
	}

	public void setDrugtype(Drugtype drugtype) {
		this.drugtype = drugtype;
	}

	public String getDrname() {
		return this.drname;
	}

	public void setDrname(String drname) {
		this.drname = drname;
	}

	public Integer getDrsum() {
		return this.drsum;
	}

	public void setDrsum(Integer drsum) {
		this.drsum = drsum;
	}

	public Double getDrprice() {
		return this.drprice;
	}

	public void setDrprice(Double drprice) {
		this.drprice = drprice;
	}

	public Integer getDrstate() {
		return this.drstate;
	}

	public void setDrstate(Integer drstate) {
		this.drstate = drstate;
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

	public Set getDrugandprescriptons() {
		return this.drugandprescriptons;
	}

	public void setDrugandprescriptons(Set drugandprescriptons) {
		this.drugandprescriptons = drugandprescriptons;
	}

	public Set getDruganddepartses() {
		return this.druganddepartses;
	}

	public void setDruganddepartses(Set druganddepartses) {
		this.druganddepartses = druganddepartses;
	}

}