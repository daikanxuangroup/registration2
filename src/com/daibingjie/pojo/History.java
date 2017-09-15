package com.daibingjie.pojo;

import java.util.Date;

/**
 * 病例表
 */

public class History implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer hiid;
	private Prescripton prescripton;
	private Doctors doctors;
	private Cards cards;
	private Date hidate;
	private String brief;
	private Integer deal;
	private String by1;
	private Integer by2;

	// Constructors

	/** default constructor */
	public History() {
	}
	

	public History(Integer hiid, Prescripton prescripton, Doctors doctors, Cards cards, Date hidate, String brief,
			Integer deal, String by1, Integer by2) {
		super();
		this.hiid = hiid;
		this.prescripton = prescripton;
		this.doctors = doctors;
		this.cards = cards;
		this.hidate = hidate;
		this.brief = brief;
		this.deal = deal;
		this.by1 = by1;
		this.by2 = by2;
	}



	// Property accessors

	public Integer getHiid() {
		return this.hiid;
	}

	public void setHiid(Integer hiid) {
		this.hiid = hiid;
	}

	public Prescripton getPrescripton() {
		return this.prescripton;
	}

	public void setPrescripton(Prescripton prescripton) {
		this.prescripton = prescripton;
	}

	public Doctors getDoctors() {
		return this.doctors;
	}

	public void setDoctors(Doctors doctors) {
		this.doctors = doctors;
	}


	public Cards getCards() {
		return cards;
	}


	public void setCards(Cards cards) {
		this.cards = cards;
	}


	public Date getHidate() {
		return this.hidate;
	}

	public void setHidate(Date hidate) {
		this.hidate = hidate;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public Integer getDeal() {
		return this.deal;
	}

	public void setDeal(Integer deal) {
		this.deal = deal;
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