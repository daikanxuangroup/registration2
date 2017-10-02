package com.dkx.util;

/**
 * 职称类型
 * 
 * @author DaiKanxuan
 * @version 2017年9月27日
 */
public enum PTitle {
	Resident(1,"住院医师"),
	physician(2,"主治医师"),
	asschief(3,"副主任医师"),
	archiater(4,"主任医师");
	
	public Integer code;
	public String title;
	private PTitle(int code, String title) {
		this.code = code;
		this.title = title;
	}
	
	
	public static PTitle getTitle(int code){
		for (PTitle pt	 : values()) {
			if(pt.code == code){
				return pt;
			}
		}
		return Resident;
	}
	
	public static PTitle getCode(String title){
		for (PTitle pt	 : values()) {
			if(pt.title.equals(title)){
				return pt;
			}
		}
		return Resident;
	}
}
