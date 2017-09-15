package com.daibingjie.pojo;

/**
 * 挂号单表
 */

public class Registration implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer rid;
	private Cards cards;
	private Bookable bookable;
	private Integer snum;
	private Integer state;
	private String by1;
	private Integer by2;

	
	

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Registration(Integer rid, Cards cards, Bookable bookable, Integer snum, Integer state, String by1,
			Integer by2) {
		super();
		this.rid = rid;
		this.cards = cards;
		this.bookable = bookable;
		this.snum = snum;
		this.state = state;
		this.by1 = by1;
		this.by2 = by2;
	}

	/** minimal constructor */
	public Registration(Integer rid) {
		this.rid = rid;
	}

	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Cards getCards() {
		return this.cards;
	}

	public void setCards(Cards cards) {
		this.cards = cards;
	}

	public Bookable getBookable() {
		return this.bookable;
	}

	public void setBookable(Bookable bookable) {
		this.bookable = bookable;
	}

	public Integer getSnum() {
		return this.snum;
	}

	public void setSnum(Integer snum) {
		this.snum = snum;
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