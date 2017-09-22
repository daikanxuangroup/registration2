package com.daibingjie.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Doctors;


@Repository("loginMapper")
public interface LoginMapper {
	
	@Select("select * from admins where aexist=1 and  aname=#{aname} and pwd=#{pwd}")
	Admins find(@Param("aname") String aname,@Param("pwd") String pwd);
	
	@Select("select doid, doname,title, deid  from doctors where doid= #{doid}")
	Doctors findDeid(@Param("doid") Integer doid);
	
	
	@Update("update admins set pwd=#{pwd2} where pwd=#{pwd} and aname=#{aname}")
	int updapwd(@Param("aname") String aname,@Param("pwd") String pwd,@Param("pwd2") String pwd2);
	
	@Update("update admins set times=sysdate where pwd=#{pwd} and aname=#{aname}")
	int updatetime(@Param("aname") String aname,@Param("pwd") String pwd);
}
