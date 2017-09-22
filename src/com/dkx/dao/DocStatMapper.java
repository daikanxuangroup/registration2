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
	@Select("select t.dyid,t.dyname,count(d.drid) dystate from drugtype t left join drug d on  t.dyid = d.dyid   group by t.dyid,t.dyname")
	List<Drugtype> statTypes();
	
	//统计药品数量
	@Select("select d.drid,d.drname,d.drsum from drug d where d.dyid =#{dyid} ")
	List<Drug> statDrugs(@Param("dyid") Integer dyid);

	//查各类型销量
	@Select("select dy.dyid, dy.dyname ,count(dp.drid) by2 from (drugtype dy left join drug dr on dy.dyid=dr.dyid ) "
			+ "left join (select * from  drugandprescripton dp left join prescripton pp on pp.prid=dp.prid  "
			+ "where to_char(pp.prdate,'yyyy-MM') =#{mon} )dp "
			+ "on  dr.drid = dp.drid where dy.dyid=#{dyid}  group by  dy.dyid,dy.dyname  ")
	Drugtype statSalDt(@Param("dyid") Integer dyid, @Param("mon") String mon);

	@Select("select dr.drid,dr.drname,count(dp.drid) by2 from drug dr left join (select * from  drugandprescripton dp "
			+ "left join prescripton pp on pp.prid=dp.prid where to_char(pp.prdate,'yyyy-MM') =#{mon}  )dp on dr.drid "
			+ "= dp.drid where dr.drid = #{drid} group by dr.drid,dr.drname")
	Drug statSalDr(@Param("drid") Integer drid,@Param("mon") String mon);
	
}
