package com.liujiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Departs;
import com.daibingjie.pojo.Doctors;
import com.liujiang.dao.DepartsMapper;

@Service("departsService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DepartsService {

	@Resource(name="departsMapper")
	private DepartsMapper departsMapper;
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int add(Departs departs){
		return departsMapper.add(departs);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int modify(Departs departs){
		return departsMapper.modify(departs);
	}
	
	public Departs findById(Integer deid){
		return departsMapper.findById(deid);
	}
	
	
	public List<Departs> findAll(){
		return departsMapper.findAll();
	}
	public int findDoc(Integer deid){
		return departsMapper.findDoc(deid);
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int update(Integer deid,Integer deexist){
		return departsMapper.update(deid, deexist);
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int modifyAll(Departs departs){
		return departsMapper.modifyAll(departs);
	}
	
	
}
