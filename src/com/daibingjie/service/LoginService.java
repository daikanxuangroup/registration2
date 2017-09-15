package com.daibingjie.service;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import com.daibingjie.dao.LoginMapper;
import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Doctors;


@Service("loginService")
public class LoginService {
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	public Admins find( String aname, String pwd){
		return loginMapper.find(aname, pwd);
		
	}
	
	
	public Doctors findDeid( Integer doid){
		return loginMapper.findDeid(doid);
		
	}
}
