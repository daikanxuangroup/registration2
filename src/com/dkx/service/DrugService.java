package com.dkx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkx.dao.DrugMapper;
import com.dkx.pojo.Departs;
import com.dkx.pojo.Drug;
import com.dkx.pojo.Drugtype;

@Service("drugService")
@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
public class DrugService {
	
	@Resource(name="drugMapper")
	private DrugMapper mapper;
	
	//查药
	public List<Drug> findAll(Double p1,Double p2){
		return mapper.findAll(p1, p2);
	}
	public Drug findById(Integer drid){
		return mapper.findDrug(drid);
	}
	

	//改状态
	@Transactional(isolation = Isolation.DEFAULT,propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public int updateDrugState(Integer drid,Integer drstate){
		return mapper.drugState(drid, drstate);
	}
	
	//新增药品 及与科室关系
	@Transactional(isolation = Isolation.DEFAULT,propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public int  addDrug(Drug drug,int[] deid){
				mapper.add(drug);
			 int drid=	mapper.findseq();
			 for(int i=0;i<deid.length;i++){
				 mapper.addDrugDe(drid, deid[i]);
			 }
		return 1;
		
	}
	//修改药品及科室关系
	@Transactional(isolation = Isolation.DEFAULT,propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public int modifyDrug(Drug drug,int[] deid){
		mapper.modifyDrug(drug);
		Integer drid = drug.getDrid();
		mapper.removeDrDe(drid);
		for(int i=0;i<deid.length;i++){
			 mapper.addDrugDe(drid, deid[i]);
		 }
		return 1;
	}
	
	//可用类型
	public List<Drugtype> findAllDy(){
		return mapper.findAllDy();
	}
	
	// 全部可用科室
	public  List<Departs> findDep(){
		return mapper.findDep();
	}
	
	//查药和科室关系
	public List<Departs> findDrDe(Integer drid){
		return mapper.findDrDe(drid);
	}
}
