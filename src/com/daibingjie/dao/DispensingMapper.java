package com.daibingjie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Prescripton;

@Repository("dispensingMapper")
public interface DispensingMapper {
	
	List<Prescripton> listPrescripton();
	// 充值
	@Update("update cards set ramaining=ramaining+#{price} where cid=#{cid}")
	int updateCards(@Param("price") Double price,@Param("cid") Integer cid);
	//扣费
	@Update("update cards set ramaining=ramaining-#{price} where cid=#{cid}")
	int updateCards2(@Param("price") Double price,@Param("cid") Integer cid);
	//修改药方状态
	@Update("  update  prescripton set drstate=0 where prid=#{prid}")
	int updateprescripton(@Param("prid") Integer prid );
	
	// 修改药品数量
	@Update("update drug set drsum = drsum-#{drsum} where drid=#{drid}")
	int updateDrug(@Param("drsum") Integer drsum,@Param("drid") Integer drid);
	
}
