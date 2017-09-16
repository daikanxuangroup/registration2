package com.daibingjie.pojo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 科室表
 */

public class Departs implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer deid;
	private String dename;
	private String intro;
	private Integer deexist;
	private String by1;
	private Integer by2;
	private Set druganddepartses = new HashSet(0);
	private Set doctorses = new HashSet(0);
	//科室药方内药品集合
	private Map<Integer, Druganddeparts> items =new HashMap<Integer,Druganddeparts >();//

	/** default constructor */
	public Departs() {
	}

	/** minimal constructor */
	public Departs(Integer deid, String dename, Integer deexist) {
		this.deid = deid;
		this.dename = dename;
		this.deexist = deexist;
	}

	/** full constructor */
	public Departs(Integer deid, String dename, String intro, Integer deexist, String by1, Integer by2,
			Set druganddepartses, Set doctorses) {
		this.deid = deid;
		this.dename = dename;
		this.intro = intro;
		this.deexist = deexist;
		this.by1 = by1;
		this.by2 = by2;
		this.druganddepartses = druganddepartses;
		this.doctorses = doctorses;
	}

	// Property accessors

	public Integer getDeid() {
		return this.deid;
	}

	public void setDeid(Integer deid) {
		this.deid = deid;
	}

	public String getDename() {
		return this.dename;
	}

	public void setDename(String dename) {
		this.dename = dename;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getDeexist() {
		return this.deexist;
	}

	public void setDeexist(Integer deexist) {
		this.deexist = deexist;
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

	public Set getDruganddepartses() {
		return this.druganddepartses;
	}

	public void setDruganddepartses(Set druganddepartses) {
		this.druganddepartses = druganddepartses;
	}

	public Set getDoctorses() {
		return this.doctorses;
	}

	public void setDoctorses(Set doctorses) {
		this.doctorses = doctorses;
	}

	public Map<Integer, Druganddeparts> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Druganddeparts> items) {
		this.items = items;
	}
	

}