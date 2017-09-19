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
	
	//查所有药
	List<Drug> findAll(@Param("price1")Double price1,@Param("price2")Double price2);
	
	//查药
	@Select("select * from drug where drid = #{drid}")
	Drug findDrug(Integer drid);
	
	//改状态
	@Update("update drug set drstate = #{drstate} where drid = #{drid}")
	int drugState(@Param("drid")Integer drid,@Param("drstate")Integer drstate);

	//改类别状态
	@Update("update drugtype set dystate = #{dystate} where dyid = #{dyid}")
	int typeState(@Param("dyid")Integer dyid,@Param("dystate")Integer dystate);
	
	//新增药品
	@Insert("insert into drug(drid,dyid,drname,drsum,drprice,drstate) values (#{drid},#{dyid},#{drname},#{drsum},#{drprice},1 )")
	@SelectKey(keyProperty="drid",statement = "select seq_drug.nextval from dual",resultType = int.class,before = true)
	int add(Drug drug);
	
	//可用的药品类型
	@Select("select dyid,dyname,dystate from DRUGTYPE where dystate = 1")
	List<Drugtype> findUsedDy();
	
	//查询所有可用科室
	@Select("select * from departs where deexist=1 ")
	List<Departs> findDep();
	
	//查药品主键
	@Select("select seq_drug.currval from dual")
	int findseq();
	
	//新增药品科室关系
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

	//全部类型
	@Select("select dyid,dyname,dystate from DRUGTYPE ")
	List<Drugtype> findAllType();

	//类型中可用药品
	@Select("select count(*) from drug where dyid = #{dyid} and drstate = 1")
	int useDrByTp(@Param("dyid")Integer dyid);

	//新增类型
	@Insert("insert into drugtype(dyid,dyname,dystate) values (#{dyid},#{dyname},1)")
	@SelectKey(keyProperty="dyid",statement="select seq_drugtype.nextval from dual",
	resultType=String.class,before=true)
	int addType (@Param("dyname")String dyname);
	
	//类型id
	@Select("select seq_drugtype.currval from dual")
	int findDyid();

}
