package com.daibingjie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.By2State;
import com.daibingjie.pojo.Cards;
import com.daibingjie.pojo.Departs;
import com.daibingjie.pojo.Druganddeparts;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.History;
import com.daibingjie.pojo.Prescripton;
import com.daibingjie.pojo.Registration;

@Repository("doctorBusMapper")
public interface DoctorBusMapper {
	/**
	 * 
	 * 医生生界面初始化的方法
	 */
	List<Registration> findPat(@Param("doid")Integer doid);

	/**
	 * 点击病人查看 信息 
	 */
	Cards findcard(Integer cid);
	
	/**
	 * 给病人添加病例
	 * @return
	 */
	@Insert(" insert into history(hiid,cid,doid,prid,brief,deal) values"
			+ "(#{hiid},#{cid},#{doid},#{prid},#{brief},#{deal})")
	@SelectKey(keyProperty="hiid",statement="select seq_history.nextval from dual",
	resultType=int.class,before=true)
	int allHistory(
			@Param("cid")Integer pid,
			@Param("doid")Integer doid,
			@Param("prid")Integer prid,
			@Param("brief")String brief,
			@Param("deal")Integer deal);
	
	/**
	 * 查看病人药方,点击药方选项 能看到病人在本部门历史用药情况
	 * 
	 */
	@Select(" select * from prescripton p left join doctors d on p.doid =d.doid "
			+ " where  d.deid=#{deid} and p.cid=#{cid}  order by prdate desc")
	 List<Prescripton> findPers(
			 @Param("cid")Integer cid,
			 @Param("deid")Integer deid);
	
	/**
	 * 点击药方 创建 药方单 并拿到 药方id ,初始化未结账 1 
	 */
	@Insert("insert into prescripton(prid,cid,doid,drstate) values"
			+ "(#{prid},#{cid},#{doid},1)")
	@SelectKey(keyProperty="prid",statement="select seq_prescripton.nextval from dual",
	resultType=int.class,before=true)
	int allPrescripton (
			@Param("cid") Integer cid,
			@Param("doid") Integer doid);	
	/**
	 * 拿到 药方id
	 */
	@Select("select seq_prescripton.currval from dual")
	int findprid(); 
	
	/**
	 * 点击 查看药方项
	 */
	List<Drugandprescripton> findDrug(@Param("prid") Integer prid);
		
	/**
	 * 添加药方项
	 */
	@Insert(" insert into drugandprescripton(drid,prid,drnum) values"
			+ "(#{drid},#{prid},#{drnum})")
	int alldrugpres(
			@Param("drid")Integer drid,
			@Param("prid")Integer prid,
			@Param("drnum")Integer drnum);
	/**
	 * 修改药方项
	 */
	@Update(" update drugandprescripton set drnum=@{drnum}"
			+ " where drid=@{drid} and prid=#{prid}")
	int updatedrug(
			@Param("drnum") Integer drnum,
			@Param("drid") Integer drid,
			@Param("prid") Integer prid);
	
	/**
	 *删除药方项
	 */
	@Delete("delete from drugandprescripton "
			+ "where drid=#{drid} and prid=#{prid}")
	int deletedrug(
			@Param("drid") Integer drid,
			@Param("prid") Integer prid);
	
	/**
	 * 查看病人病例的方法
	 */
	List<History> findHis(@Param("cid") Integer cid ,@Param("deid") Integer deid );
	
	/**
	 * 修改订单状态的
	 */
	
	@Update("update  registration set state=#{state} where rid=#{rid}")
	int updarig(@Param("rid") Integer rid,@Param("state") Integer state);
	
	
	@Update("update registration  set by2=#{by2} where rid =#{rid}")
	int updaby2(@Param("rid") Integer by2,@Param("by2") Integer state);
	
	/**
	 * 查询药品 部门和数量
	 * @return
	 */
	
	List<Druganddeparts> finddru(
			@Param("deid") Integer deid,
			@Param("price1")Double price1,
			@Param("price2")Double price2);
	
		/*	   今天挂号单卡号状态*/
	@Select("select state,c.by2 from registration r , bookable b,cards c where r.bid=b.bid and c.cid=r.cid and  r.rid=#{rid} and b.bdate= trunc(sysdate) and state > 0")
	By2State findBystate(@Param("rid") Integer rid);
	
	/*	  查看备用2 里是否有今天的药方*/
	@Select(" select c.by2 from registration r , bookable b,cards c where r.bid=b.bid and c.cid=r.cid and r.rid=#{rid} and b.bdate= trunc(sysdate) and state > 0 ")
	int findby2(@Param("rid") Integer rid);
	
	
	
}
