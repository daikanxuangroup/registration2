package com.dkx.pojo;

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
	public Integer getDeid() {
		return deid;
	}
	public void setDeid(Integer deid) {
		this.deid = deid;
	}
	public String getDename() {
		return dename;
	}
	public void setDename(String dename) {
		this.dename = dename;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getDeexist() {
		return deexist;
	}
	public void setDeexist(Integer deexist) {
		this.deexist = deexist;
	}
	public String getBy1() {
		return by1;
	}
	public void setBy1(String by1) {
		this.by1 = by1;
	}
	public Integer getBy2() {
		return by2;
	}
	public void setBy2(Integer by2) {
		this.by2 = by2;
	}
	public Departs(Integer deid, String dename, String intro, Integer deexist, String by1, Integer by2) {
		this.deid = deid;
		this.dename = dename;
		this.intro = intro;
		this.deexist = deexist;
		this.by1 = by1;
		this.by2 = by2;
	}
	public Departs() {
		super();
		// TODO Auto-generated constructor stub
	}

}