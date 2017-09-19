package com.daibingjie.service;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daibingjie.dao.LoginMapper;
import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Doctors;


@Service("loginService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class LoginService {
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	public Admins find( String aname, String pwd){
		return loginMapper.find(aname, pwd);
		
	}
		
	public Doctors findDeid( Integer doid){
		return loginMapper.findDeid(doid);
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,rollbackFor=Exception.class)
	public int updapwd(String aname, String pwd, String pwd2){
		System.out.println(aname+pwd+pwd2);
		return loginMapper.updapwd(aname, pwd, pwd2);
	}
}
