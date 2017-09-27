package com.liujiang.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.liujiang.pojo.Departs;
import com.liujiang.pojo.Doctors;
import com.daibingjie.pojo.Admins;
import com.liujiang.dao.DoctorsMapper;

@Service("doctorsService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class DoctorsService {
	
	@Resource(name="doctorsMapper")
	private DoctorsMapper doctorsMapper;
	
	
	public List<Doctors> findAll(){
		return doctorsMapper.findAll();
	}
	
	public Doctors findById(Integer doid){
		return doctorsMapper.findById(doid);
	}
	
	public List<Departs> findDep(){
		return doctorsMapper.findDep();
	}
	
	public int select1(Integer doid){
		return doctorsMapper.select1(doid);
	}
	public int select2(Integer doid){
		return doctorsMapper.select2(doid);
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updateState(Integer doid,Integer doexist){
		Integer aid = doctorsMapper.findDocAid(doid);
		if(aid!=null)
			doctorsMapper.updateAdState(aid,doexist);
		return doctorsMapper.updateState(doid, doexist);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int modify(Doctors doctors){
		return doctorsMapper.modify(doctors);
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int add(Doctors doctors,String aname){
		doctorsMapper.add(doctors);
		int doid = doctorsMapper.findDoc();
		return doctorsMapper.addAdmins(doid, aname);
	}
	public Admins findDoid(Integer doid){
		return doctorsMapper.findDoid(doid);
	}
	/*@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int addAdmins(Admins admins){
		return doctorsMapper.addAdmins(admins);
	}*/
	
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int modifyState(Integer doid,Integer doexist){
		return doctorsMapper.modifyState(doid, doexist);
	}

	public int findUsername(String aname) {
		// TODO Auto-generated method stub
		return doctorsMapper.findUsername(aname);
	}

	public int ckDestate(Integer doid) {
		// TODO Auto-generated method stub
		return doctorsMapper.ckDeState(doid);
	}

	public Integer ckState(Integer doid) {
		// TODO Auto-generated method stub
		return doctorsMapper.ckState(doid);
	}
}
