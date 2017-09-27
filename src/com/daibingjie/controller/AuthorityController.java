package com.daibingjie.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.Admins;
import com.daibingjie.service.AuthorityService;
@AuthPassport
@Controller
public class AuthorityController {
	
	
	
	@Resource(name="authorityService")
	public AuthorityService authorityService;
	
	@AuthPassport
	@RequestMapping("listAdmin")
	public String listAdmin(ModelMap modelMap){
		List<Admins> list =authorityService.liatAadmin();		
		modelMap.put("list", list);
		return "authority/admin";
		
		
	}
	@AuthPassport
	@RequestMapping("listSignals")
	public String listSignals(ModelMap modelMap){
		List<Admins> list =authorityService.liatSignals();	
		modelMap.put("list", list);
		return "authority/signals";
		
		
	}
	@AuthPassport
	@RequestMapping("dispensing")
	public String dispensing(ModelMap modelMap){
		
		List<Admins> list =authorityService.dispensing();	
		modelMap.put("list", list);
		return "authority/dispensing";
	
		
		
		
	}
	
	
	
	
	
	@AuthPassport
	@RequestMapping("updateAdmins")
	@ResponseBody
	public String updateAdmins(@RequestParam("aid")Integer aid,@RequestParam("aexist") Integer aexist){
		String mgs="false";
		if(0<authorityService.updateAdmins(aexist, aid)){
			mgs="true";
		}	
		return mgs;		
	}
	@AuthPassport
	@RequestMapping("addAdmins")
	@ResponseBody
	public String addAdmins(@RequestParam("state")Integer state,
		@RequestParam("aname") String aname,@RequestParam("by1")String by1){	
		String mgs="false";
		if(0==authorityService.findname(aname)){
			if(0<authorityService.addAdmins(aname, state, by1)){
				mgs="true";
			}				
		}	
		return mgs;	
	}
	                                                                                                                                                                                                                                                                                                                                                              
	@AuthPassport
	@RequestMapping("tempAll")
	public String tempAll(ModelMap modelMap,@RequestParam("state")Integer state){

		modelMap.put("state", state);
		return "authority/addAdmins";
		
		
	}

}
