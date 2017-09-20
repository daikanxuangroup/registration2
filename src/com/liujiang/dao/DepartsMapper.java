package com.liujiang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Departs;
import com.daibingjie.pojo.Doctors;

@Repository("departsMapper")
public interface DepartsMapper {

	// 添加科室
	@Insert("insert into departs(deid,dename,intro,deexist) values(#{deid},#{dename},#{intro,jdbcType=VARCHAR},#{deexist})")
	@SelectKey(keyProperty="deid",statement="select seq_departs.nextval from dual",resultType=int.class,before=true)
	int add(Departs departs); 
	
	// 修改科室状态
	@Update("update departs set deexist=#{deexist} where deid=#{deid}")
	int modify(Departs departs);
	
	// 根据科室id查询科室信息
	@Select("select deid,dename,intro,deexist,by1,by2 from departs where deid=#{deid}")
	Departs findById(Integer deid);
	
	// 查询科室信息
	@Select("select deid,dename,intro,deexist,by1,by2 from departs where 1=1")
	List<Departs> findAll();
	
	// 是否可以停用该科室(判断该科室下如若有医生则不能停用,没则可以停用)
	@Select("select count(doname) from doctors where deid=#{deid}")
	int findDoc(Integer deid);
	
	// 修改科室状态
	@Update("update departs set deexist=#{deexist} where deid=#{deid}")
	int update(@Param("deid") Integer deid,@Param("deexist") Integer deexist);
	
	// 修改科室
	@Update("update departs set intro=#{intro,jdbcType=VARCHAR}  where deid=#{deid}")
	int modifyAll(Departs departs);
}
