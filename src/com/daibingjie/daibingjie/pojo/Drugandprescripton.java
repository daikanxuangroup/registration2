package com.daibingjie.pojo;

import java.math.BigDecimal;
/**
 * 药品与药方关系表
 */
public class Drugandprescripton implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Integer prid; // 药方id
	private Integer drid; // 药品id
	private Drug drug;  //药品
	private Integer drnum;//数量
	private Double sum;//小计价格
	private String by1;
	private Integer by2;
	private Prescripton prescripton;
	
		
	public Drugandprescripton() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prescripton getPrescripton() {
		return prescripton;
	}
	public void setPrescripton(Prescripton prescripton) {
		this.prescripton = prescripton;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Drug getDrug() {
		return drug;
	}
	public void setDrug(Drug drug) {
		this.drug = drug;
	}
	public Integer getPrid() {
		return prid;
	}
	public void setPrid(Integer prid) {
		this.prid = prid;
	}
	public Integer getDrnum() {
		return drnum;
	}
	/*
	 * 每当药品数量被修改，则重新计算小计价格
	 */
	public void setDrnum(Integer drnum) {
		
		this.drnum = drnum;
		//赋值给总价
/*		Double s1 = this.drnum*drug.getDrprice();	
		this.sum=s1;*/
		
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
	public Integer getDrid() {
		return drid;
	}
	public void setDrid(Integer drid) {
		this.drid = drid;
	}
	public Drugandprescripton(Integer prid, Integer drid, Drug drug, Integer drnum, Double sum, String by1, Integer by2,
			Prescripton prescripton) {
		super();
		this.prid = prid;
		this.drid = drid;
		this.drug = drug;
		this.drnum = drnum;
		this.sum = sum;
		this.by1 = by1;
		this.by2 = by2;
		this.prescripton = prescripton;
	}

	

}