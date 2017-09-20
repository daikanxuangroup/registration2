package com.dkx.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkx.dao.DocStatMapper;
import com.dkx.pojo.Drug;
import com.dkx.pojo.Drugtype;

@Service("statisticsService")
@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
public class StatisticsService {
	
	@Resource(name="dsMapper")
	private DocStatMapper dsmapper;
	
	//总病人列表
	public List<Integer> findAllP(Integer doid){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			Integer num = dsmapper.findAllP(doid, i);
			list.add(num);
		}
		return list;
	}
	
	//开药病人列表
	public List<Integer> findDruP(Integer doid){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			Integer num = dsmapper.findDrugP(doid, i);
			list.add(num);
		}
		return list;
	}
		
	//回家病人列表
	public List<Integer> findHomP(Integer doid){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			Integer num = dsmapper.findHomeP(doid, i);
			list.add(num);
		}
		return list;
	}
		
	//住院病人列表
	public List<Integer> findHosP(Integer doid){
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			Integer num = dsmapper.findHosP(doid, i);
			list.add(num);
		}
		return list;
	}

	public List<Drugtype> statTypes() {
		// TODO Auto-generated method stub
		return dsmapper.statTypes();
	}

	public List<Drug> statDrugs(Integer dyid){
		return dsmapper.statDrugs(dyid);
	}
}
