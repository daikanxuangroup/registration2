package com.daibingjie.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 药品类别表
 */

public class Drugtype implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer dyid;
	private String dyname;
	private Integer dystate;
	private String by1;
	private Integer by2;
	private Set drugs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Drugtype() {
	}

	/** minimal constructor */
	public Drugtype(Integer dyid, String dyname, Integer dystate) {
		this.dyid = dyid;
		this.dyname = dyname;
		this.dystate = dystate;
	}

	/** full constructor */
	public Drugtype(Integer dyid, String dyname, Integer dystate, String by1, Integer by2, Set drugs) {
		this.dyid = dyid;
		this.dyname = dyname;
		this.dystate = dystate;
		this.by1 = by1;
		this.by2 = by2;
		this.drugs = drugs;
	}

	// Property accessors

	public Integer getDyid() {
		return this.dyid;
	}

	public void setDyid(Integer dyid) {
		this.dyid = dyid;
	}

	public String getDyname() {
		return this.dyname;
	}

	public void setDyname(String dyname) {
		this.dyname = dyname;
	}

	public Integer getDystate() {
		return this.dystate;
	}

	public void setDystate(Integer dystate) {
		this.dystate = dystate;
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

	public Set getDrugs() {
		return this.drugs;
	}

	public void setDrugs(Set drugs) {
		this.drugs = drugs;
	}

}