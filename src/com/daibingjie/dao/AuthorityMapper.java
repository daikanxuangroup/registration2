package com.daibingjie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Admins;

@Repository("authorityMapper")
public interface AuthorityMapper {
	
	
	@Select("select * from admins where state = 1")
	List<Admins> liatAadmin(); 
	
	@Select("select * from admins where state = 3")
	List<Admins> liatSignals(); 
	
	@Select("select * from admins where state = 4")
	List<Admins> dispensing(); 
	
	@Insert("insert into admins(aid,aname,pwd,state,by1) values(#{aid},#{aname},'111111',#{state },#{by1})")
	@SelectKey(keyProperty="aid",statement="select seq_admins.nextval from dual",
	resultType=int.class,before=true)
	int addAdmins(@Param("aname") String aname,@Param("state") Integer state,@Param("by1") String by1);
	
	@Update("update admins set aexist=#{aexist} where aid = #{aid}")
	int updateAdmins(@Param("aexist") Integer aexist , @Param("aid") Integer aid);
	
	@Select(" select count(*) from admins where aname=#{aname}")
	int findname(@Param("aname") String aname);
	
}
