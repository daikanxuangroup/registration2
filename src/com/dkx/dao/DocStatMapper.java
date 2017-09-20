package com.dkx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.dkx.pojo.Drug;
import com.dkx.pojo.Drugtype;

@Repository("dsMapper")
public interface DocStatMapper {
	
	@Select("select  count(*) from history h where h.doid = #{doid} and to_number(to_char(h.hidate,'mm')) = #{month} and to_number(to_char(h.hidate,'yyyy')) = 2017")
	Integer findAllP(@Param("doid") Integer doid,@Param("month") Integer month );
	
	//回家人数
	@Select("select  count(*) from history h where h.doid = #{doid} and h.deal=1 and to_number(to_char(h.hidate,'mm')) = #{month} and to_number(to_char(h.hidate,'yyyy')) = 2017")
	Integer findHomeP(@Param("doid") Integer doid,@Param("month") Integer month );
		
	//开药人数
	@Select("select  count(*) from history h where h.doid = #{doid} and h.deal=2 and to_number(to_char(h.hidate,'mm')) = #{month} and to_number(to_char(h.hidate,'yyyy')) = 2017")
	Integer findDrugP(@Param("doid") Integer doid,@Param("month") Integer month );
	
	//住院人数
	@Select("select  count(*) from history h where h.doid = #{doid} and h.deal=3 and to_number(to_char(h.hidate,'mm')) = #{month} and to_number(to_char(h.hidate,'yyyy')) = 2017")
	Integer findHosP(@Param("doid") Integer doid,@Param("month") Integer month );
	
	//统计每类药数量 --借dystate字段装数量
	@Select("select t.dyid,t.dyname,count(d.drid) dystate from drugtype t left join drug d on  t.dyid = d.dyid and d.drstate = 1 where t.dystate = 1 group by t.dyid,t.dyname")
	List<Drugtype> statTypes();
	
	//统计药品数量
	@Select("select d.drid,d.drname,d.drsum from drug d where d.dyid =#{dyid} and d.drstate = 1 ")
	List<Drug> statDrugs(@Param("dyid") Integer dyid);
}
