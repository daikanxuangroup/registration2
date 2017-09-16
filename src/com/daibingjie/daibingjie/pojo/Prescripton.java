package com.daibingjie.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 药方表
 */

public class Prescripton implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer prid;
	private Doctors doctors;
	private Cards cards;
	private Date prdate;
	private Double dtotal;
	private Integer drstate;
	private double total; //总价
	private String by1;
	private Integer by2;
	private Set drugandprescriptons = new HashSet(0);
	private Set histories = new HashSet(0);
	//订单药方内药品集合
	private Map<Integer, Drugandprescripton> items =new HashMap<Integer,Drugandprescripton >();
	
	
	
	public Prescripton(Integer prid, Doctors doctors, Cards cards, Date prdate, Double dtotal, Integer drstate,
			double total, String by1, Integer by2, Set drugandprescriptons, Set histories,
			Map<Integer, Drugandprescripton> items) {
		super();
		this.prid = prid;
		this.doctors = doctors;
		this.cards = cards;
		this.prdate = prdate;
		this.dtotal = dtotal;
		this.drstate = drstate;
		this.total = total;
		this.by1 = by1;
		this.by2 = by2;
		this.drugandprescriptons = drugandprescriptons;
		this.histories = histories;
		this.items = items;
	}


	public Prescripton() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Map<Integer, Drugandprescripton> getItems() {
		return items;
	}


	public void setItems(Map<Integer, Drugandprescripton> items) {
		this.items = items;
	}


	public double getTotal() {
		double sum=0;
		//map 中拿出来
		Iterator<Drugandprescripton> it= items.values().iterator();		
		//累加订单项的集合每个小计价格
		while(it.hasNext()){
			sum+=it.next().getSum();
		}
		this.total=sum;	
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}


	public Integer getPrid() {
		return prid;
	}
	public void setPrid(Integer prid) {
		this.prid = prid;
	}

	public Doctors getDoctors() {
		return doctors;
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


	public Date getPrdate() {
		return prdate;
	}
	public void setPrdate(Date prdate) {
		this.prdate = prdate;
	}
	public Double getDtotal() {
		return dtotal;
	}
	public void setDtotal(Double dtotal) {
		this.dtotal = dtotal;
	}
	public Integer getDrstate() {
		return drstate;
	}
	public void setDrstate(Integer drstate) {
		this.drstate = drstate;
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
	public Set getDrugandprescriptons() {
		return drugandprescriptons;
	}
	public void setDrugandprescriptons(Set drugandprescriptons) {
		this.drugandprescriptons = drugandprescriptons;
	}
	public Set getHistories() {
		return histories;
	}
	public void setHistories(Set histories) {
		this.histories = histories;
	}
	/*
	 * 添加药方的药品业务方法
	 */
	public void addItem(Drug drug){
		//根据新添加药品的drid值获得
		Drugandprescripton item =items.get(drug.getDrid());
		
		if(item==null){
			//没有该药方时候
			item =new Drugandprescripton();
			item.setDrug(drug);
			item.setDrnum(1);;;
			this.items.put(drug.getDrid(), item);			
		}else{
			//药方有该项 修改数量加1
			int num =item.getDrnum();
			item.setDrnum(num+1);
		}
	}
}