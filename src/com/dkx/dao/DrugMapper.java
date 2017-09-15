package com.dkx.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dkx.pojo.Departs;
import com.dkx.pojo.Drug;
import com.dkx.pojo.Drugtype;



@Repository("drugMapper")
public interface DrugMapper {
	
	List<Drug> findAll(@Param("price1")Double price1,@Param("price2")Double price2);
	
	@Select("select * from drug where drid = #{drid}")
	Drug findDrug(Integer drid);
	
	//该状态
	@Update("update drug set drstate = #{drstate} where drid = #{drid}")
	int drugState(@Param("drid")Integer drid,@Param("drstate")Integer drstate);
	
	@Insert("insert into drug(drid,dyid,drname,drsum,drprice,drstate) values (#{drid},#{dyid},#{drname},#{drsum},#{drprice},1 )")
	@SelectKey(keyProperty="drid",statement = "select seq_drug.nextval from dual",resultType = int.class,before = true)
	int add(Drug drug);
	
	@Select("select dyid,dyname,dystate from DRUGTYPE where dystate = 1")
	List<Drugtype> findAllDy();
	
	@Select("select * from departs where deexist=1 ")
	List<Departs> findDep();
	
	@Select("select seq_drug.currval from dual")
	int findseq();
	
	@Insert("insert into druganddeparts(drid,deid) values(#{drid},#{deid})")
	int addDrugDe(@Param("drid") Integer drid ,@Param("deid") Integer deid);
	
	//查药品科室关系
	@Select("select t.deid,t.dename from departs t left join druganddeparts d on t.deid = d.deid where d.drid = #{drid}")
	List<Departs> findDrDe(@Param("drid") Integer drid);
	
	//删除药品科室关系
	@Delete("delete from druganddeparts where drid = #{drid}")
	int removeDrDe(@Param("drid") Integer drid);
	//修改药品
	@Update("update drug set dyid=#{dyid},drname=#{drname},drsum=#{drsum},drprice=#{drprice},drstate=#{drstate} where drid = #{drid}" )
	int modifyDrug(Drug drug);
}
