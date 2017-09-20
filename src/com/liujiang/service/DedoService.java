package com.liujiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daibingjie.pojo.Departs;
import com.daibingjie.pojo.Doctors;
import com.liujiang.dao.DedoMapper;
import com.liujiang.pojo.DepartsDoctors;

@Service("dedoService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DedoService {
	
	@Resource(name = "dedoMapper")
	private DedoMapper dedoMapper;
	
	public List<DepartsDoctors> findDedo(){
		return dedoMapper.findDedo();
	}
	
	/*public DepartsDoctors findById(Integer doid){
		return dedoMapper.findById(doid);
	}*/
	public Doctors find(Integer doid){
		return dedoMapper.find(doid);
	}
	
	public List<Departs> findByDeid(){
		return dedoMapper.findByDeid();
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int modify(Doctors doctors){
		return dedoMapper.modify(doctors);
	}

}
