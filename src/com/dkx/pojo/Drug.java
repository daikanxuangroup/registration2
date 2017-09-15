package com.dkx.pojo;



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
	private Integer drid;
	private Integer dyid;
	private Drugtype drugtype;
	private String drname;
	private Integer drsum;
	private Double drprice;
	private Integer drstate;
	private String by1;
	private Integer by2;
	private Set drugandprescriptons = new HashSet(0);
	private Set druganddepartses = new HashSet(0);
	private String dename;
	
	
	// Constructors

	public String getDename() {
		return dename;
	}

	public void setDename(String dename) {
		this.dename = dename;
	}

	/** default constructor */
	public Drug() {
	}

	/** minimal constructor */
	public Drug(Integer drid, String drname,Integer drsum, Double drprice, Integer drstate,Integer dyid) {
		this.drid = drid;
		this.drname = drname;
		this.drsum = drsum;
		this.drprice = drprice;
		this.drstate = drstate;
		this.dyid = dyid;
	}



	// Property accessors

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

	public Integer getDyid() {
		return dyid;
	}

	public void setDyid(Integer dyid) {
		this.dyid = dyid;
	}

	@Override
	public String toString() {
		return "Drug [drid=" + drid + ", dyid=" + dyid + ", drname=" + drname + ", drsum=" + drsum + ", drprice="
				+ drprice + ", drstate=" + drstate + "]";
	}

}