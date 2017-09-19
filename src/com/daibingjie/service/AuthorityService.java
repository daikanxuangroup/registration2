package com.daibingjie.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.aspectj.weaver.bcel.AtAjAttributes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import com.daibingjie.dao.AuthorityMapper;
import com.daibingjie.pojo.Admins;

@Service("authorityService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class AuthorityService {
	
	
	@Resource(name="authorityMapper")
	public AuthorityMapper authorityMapper;
	
	
	
	
	// 查看全部普通管理员
   public List<Admins> liatAadmin(){
	   List<Admins> list=authorityMapper.liatAadmin();
	return list;
		
	} 
// 查看全部发号员
   public List<Admins> liatSignals(){
	   List<Admins> list=authorityMapper.liatSignals();
	return list;
		
	} 
// 添加
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
   public int addAdmins(String aname, Integer state,String by1){
	return authorityMapper.addAdmins(aname,state,by1);		
	}
   // 启用和停用
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
   public int updateAdmins(Integer aexist , Integer aid){
	return authorityMapper.updateAdmins(aexist, aid);
		
	}

}
