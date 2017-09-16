package com.daibingjie.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.daibingjie.dao.DoctorBusMapper;
import com.daibingjie.pojo.By2State;
import com.daibingjie.pojo.Cards;
import com.daibingjie.pojo.Drug;
import com.daibingjie.pojo.Druganddeparts;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.History;
import com.daibingjie.pojo.Prescripton;
import com.daibingjie.pojo.Registration;

@Service("doctorBusService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DoctorBusService {
	
	@Resource(name="doctorBusMapper")
	public DoctorBusMapper doctorBusMapper;
	/**
	 * 
	 * 医生生界面初始化的方法
	 */
	public List<Registration> findpat(Integer boid){
		
		List<Registration> list= doctorBusMapper.findPat(boid);
		
		return list;
		
	}
	/**
	 * 点击病人查看 信息 
	 */
	public Cards findcard(Integer cid){
		
		Cards cards=doctorBusMapper.findcard(cid);
		
		return cards;
	}
	/** 
	 * 给病人添加病例  诊疗卡ID 医生ID 药方 信息  -诊断结果  方案
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int allHistory(Integer cid,Integer doid,Integer prid,String brief,
			Integer deal){
		int count = doctorBusMapper.allHistory(cid, doid, prid, brief, deal);
			return count;
			
	}
	
	/** 
	 * 给病人添加病例  诊疗卡ID 医生ID ） 信息  -诊断结果  方案  （回家不开药）
	 * 
	 */
	public int allHistory(Integer cid,Integer doid,String brief,Integer deal){
		int count = doctorBusMapper.allHistory(cid, doid, null, brief, deal);
			return count;
			
	}
	
	
	
	
	
	/**
	 * 点击药方 创建 药方单 并拿到 药方id ,初始化未结账 1 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int allPrescripton (Integer cid,Integer doid,Integer drid){
		int count =doctorBusMapper.allPrescripton(cid, doid);
		
		
		if(count>0){
			//拿到药方ID
			count=doctorBusMapper.findprid();
			//添加一个药方项
			doctorBusMapper.alldrugpres(drid, count, 1);
		}
		return count;
		
	}
	/**
	 * 添加药方项 药方ID 药品ID 数量 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int alldrugpres(Integer drid,Integer prid,Integer drnum){
		
		int count =doctorBusMapper.alldrugpres(drid, prid, drnum);
		return count;		
		
	}
	/**
	 * 修改药方项 药方ID 药品ID 数量 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updatedrug(Integer drnum,Integer drid, Integer prid){
		int count =doctorBusMapper.updatedrug(drnum, drid, prid);
		return count;
	}
	/** 
	 *删除药方项 药方ID 药品ID 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int deletedrug(Integer drid, Integer prid){
		int count =doctorBusMapper.deletedrug(drid, prid);
		return count;
	}
	/**
	 * 查看病人病例的方法 
	 */
	public  List<History> findHis(Integer cid ,Integer deid ){
		List<History> list = doctorBusMapper.findHis(cid, deid);
		
		return list;
	}
	
	/**
	 * 查看病人药方,点击药方选项 能看到病人在本部门历史用药情况
	 * 
	 */

	public List<Prescripton> findPers(Integer cid,Integer deid){
		List<Prescripton> list=doctorBusMapper.findPers(cid, deid);
		return list;
		
	}
	

	
	/**
	 * 点击 查看药方项
	 */
	public List<Drugandprescripton> findDrug( Integer prid){
		
		List<Drugandprescripton> list=doctorBusMapper.findDrug(prid);	
		
		return list;
		
	}
	/**
	 *  点击 查看药方项
	 * 
	 */
	public Map<Integer, Drugandprescripton> findMap(Integer prid){
		Map<Integer, Drugandprescripton> map=new HashMap<Integer, Drugandprescripton>();
		List<Drugandprescripton> list=doctorBusMapper.findDrug(prid);	
			// 拿出每个 订单项
		for(Drugandprescripton d:list){		
			Drug drug =new Drug();
			drug.setDrid(d.getDrid());
			drug.setDrname(d.getDrug().getDrname());
			drug.setDrprice(d.getDrug().getDrprice());
			d.setSum(d.getDrug().getDrprice()*d.getDrnum());//小计价格
			d.setDrug(drug);//一个药品信息
			map.put(d.getDrid(), d);
		}
		
		
		return map;
		
	}
		
	/**
	 * 
	 * 修改订单状态
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updarig(Integer rid,Integer state ){
		
		return doctorBusMapper.updarig(rid, state);
	}
	
	/**
	 * 修改备用状态2 
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updaby2(Integer rid,Integer by2){
		
		return doctorBusMapper.updaby2(rid, by2);
	}
	
	
	
	/**
	 * 查询药品 部门和数量
	 * @return
	 */
	public List<Druganddeparts> finddru(Integer deid,Double price1,Double price2){
		return doctorBusMapper.finddru(deid,price1,price2);
	}
	
	/**
	 *  查询订单状态
	 */
	public By2State findBystate(Integer rid){
		return doctorBusMapper.findBystate(rid);
	}

	/*	  查看备用2 里是否有今天的药方*/
	public int findby2( Integer rid){
		
		return doctorBusMapper.findby2(rid);
	}
	
	
	

	
	
}
