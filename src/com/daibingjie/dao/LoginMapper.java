package com.daibingjie.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Doctors;


@Repository("loginMapper")
public interface LoginMapper {
	
	@Select("select * from admins where aexist=1 and  aname=#{aname} and pwd=#{pwd}")
	Admins find(@Param("aname") String aname,@Param("pwd") String pwd);
	
	@Select("select doid, doname, deid  from doctors where doid= #{doid}")
	Doctors findDeid(@Param("doid") Integer doid);
}
