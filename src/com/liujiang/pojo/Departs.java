package com.liujiang.pojo;

import java.io.Serializable;

public class Departs implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer deid;
	private String dename;
	private String intro;
	private Integer deexist;
	
	public Departs() {
		
	}
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
	
	
}
