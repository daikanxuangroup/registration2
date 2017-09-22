package com.daibingjie.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daibingjie.dao.DispensingMapper;
import com.daibingjie.dao.DoctorBusMapper;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.Prescripton;

@Service("dispensingService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DispensingService {

	@Resource(name="dispensingMapper")
	private DispensingMapper dispensingMapper;
	
	@Resource(name="doctorBusMapper")
	private DoctorBusMapper doctorBusMapper;
	
	
	public List<Prescripton> listPrescripton(){
		List<Prescripton> list = dispensingMapper.listPrescripton();
		return list;
		
		
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updateCards( Double price,Integer cid){
		
		return dispensingMapper.updateCards(price, cid);
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updateCards2( Double price,Integer cid){
		
		return dispensingMapper.updateCards2(price, cid);
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int charge(Integer prid,Integer cid,Double price){
		System.out.println(prid);
		List<Drugandprescripton> list=  doctorBusMapper.findDrug(prid);// 拿出订单项
		System.out.println(list.size());
		for(Drugandprescripton d:list){

			//循环减去药品库存数量
			dispensingMapper.updateDrug(d.getDrnum(), d.getDrid());
		}
			
		dispensingMapper.updateCards2(price, cid);// 诊疗卡扣费
				
		//修改药方
		return dispensingMapper.updateprescripton(prid);
		
		
	}
}
