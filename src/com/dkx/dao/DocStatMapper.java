package com.dkx.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
}
