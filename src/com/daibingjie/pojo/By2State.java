package com.daibingjie.pojo;

import java.io.Serializable;

public class By2State implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String by1;
	private Integer by2;
	private Integer state;
	public Integer getBy2() {
		return by2;
	}
	public void setBy2(Integer by2) {
		this.by2 = by2;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}


	public By2State(String by1, Integer by2, Integer state) {
		super();
		this.by1 = by1;
		this.by2 = by2;
		this.state = state;
	}
	public String getBy1() {
		return by1;
	}
	public void setBy1(String by1) {
		this.by1 = by1;
	}
	public By2State() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
