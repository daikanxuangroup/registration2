package com.liujiang.pojo;

import java.io.Serializable;

public class DepartsDoctors implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer doid;
	private Integer deid;
	private String dename;
	private String doname;
	private String title;
	private String photo;
	private String info;
	private Integer monam;
	private Integer monpm;
	private Integer tueam;
	private Integer tuepm;
	private Integer wedam;
	private Integer wedpm;
	private Integer thuam;
	private Integer thupm;
	private Integer friam;
	private Integer fripm;
	private Integer satam;
	private Integer satpm;
	private Integer sunap;
	private Integer sumpm;
	private Integer pcreg;
	private Integer xcreg;
	private Integer doexist;
	private Double bcost;
	
	public DepartsDoctors() {
		
	}

	public DepartsDoctors(Integer doid, String dename, String doname, String title, String info, Integer doexist,
			Double bcost,Integer monam) {
		super();
		this.doid = doid;
		this.dename = dename;
		this.doname = doname;
		this.title = title;
		this.info = info;
		this.doexist = doexist;
		this.bcost = bcost;
		this.monam = monam;
	}

	

	public Integer getDeid() {
		return deid;
	}

	public void setDeid(Integer deid) {
		this.deid = deid;
	}

	public Integer getDoid() {
		return doid;
	}

	public void setDoid(Integer doid) {
		this.doid = doid;
	}

	public String getDename() {
		return dename;
	}

	public void setDename(String dename) {
		this.dename = dename;
	}

	public String getDoname() {
		return doname;
	}

	public void setDoname(String doname) {
		this.doname = doname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Integer getMonam() {
		return monam;
	}

	public void setMonam(Integer monam) {
		this.monam = monam;
	}

	public Integer getMonpm() {
		return monpm;
	}

	public void setMonpm(Integer monpm) {
		this.monpm = monpm;
	}

	public Integer getTueam() {
		return tueam;
	}

	public void setTueam(Integer tueam) {
		this.tueam = tueam;
	}

	public Integer getTuepm() {
		return tuepm;
	}

	public void setTuepm(Integer tuepm) {
		this.tuepm = tuepm;
	}

	public Integer getWedam() {
		return wedam;
	}

	public void setWedam(Integer wedam) {
		this.wedam = wedam;
	}

	public Integer getWedpm() {
		return wedpm;
	}

	public void setWedpm(Integer wedpm) {
		this.wedpm = wedpm;
	}

	public Integer getThuam() {
		return thuam;
	}

	public void setThuam(Integer thuam) {
		this.thuam = thuam;
	}

	public Integer getThupm() {
		return thupm;
	}

	public void setThupm(Integer thupm) {
		this.thupm = thupm;
	}

	public Integer getFriam() {
		return friam;
	}

	public void setFriam(Integer friam) {
		this.friam = friam;
	}

	public Integer getFripm() {
		return fripm;
	}

	public void setFripm(Integer fripm) {
		this.fripm = fripm;
	}

	public Integer getSatam() {
		return satam;
	}

	public void setSatam(Integer satam) {
		this.satam = satam;
	}

	public Integer getSatpm() {
		return satpm;
	}

	public void setSatpm(Integer satpm) {
		this.satpm = satpm;
	}

	public Integer getSunap() {
		return sunap;
	}

	public void setSunap(Integer sunap) {
		this.sunap = sunap;
	}

	public Integer getSumpm() {
		return sumpm;
	}

	public void setSumpm(Integer sumpm) {
		this.sumpm = sumpm;
	}

	public Integer getPcreg() {
		return pcreg;
	}

	public void setPcreg(Integer pcreg) {
		this.pcreg = pcreg;
	}

	public Integer getXcreg() {
		return xcreg;
	}

	public void setXcreg(Integer xcreg) {
		this.xcreg = xcreg;
	}

	public Integer getDoexist() {
		return doexist;
	}

	public void setDoexist(Integer doexist) {
		this.doexist = doexist;
	}

	public Double getBcost() {
		return bcost;
	}

	public void setBcost(Double bcost) {
		this.bcost = bcost;
	}
	

}
