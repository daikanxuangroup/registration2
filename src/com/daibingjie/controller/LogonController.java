package com.daibingjie.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;

import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Doctors;
import com.daibingjie.service.LoginService;

@Controller
@SessionAttributes(types=Integer.class,value={"doctors","state"})
public class LogonController {
	
	@Resource(name="loginService")
	public LoginService loginService;	
		/**
	   * 用户登录
	   */
	  @RequestMapping("/login")
	  public String login(@RequestParam("aname") String aname, @RequestParam("pwd") String pwd,
	      @RequestParam("verifyCode") String verifyCode, HttpServletRequest request,ModelMap modelMap) {
	    HttpSession session = request.getSession();
	    String securityCode = (String) session.getAttribute("securityCode");
	    String url = "redirect:/login.jsp";
	    String message = "";

	    if (verifyCode.equals(securityCode)) {
	      
		Admins adm = loginService.find(aname, pwd);
	      if (adm != null) {
	    	  Integer state=adm.getState();
	    	  modelMap.put("state",state);// 用来判断用户的身份
	    	  if(adm.getState()==2){
	    		 
	    		Doctors doctors=loginService.findDeid(adm.getDoid());  
	    		
	    		modelMap.put("doctors", doctors);// 医生药用的
	    	
	    /*		session.setAttribute("state",state );
	    		session.setAttribute("doctors", doctors);*/
	    		  url = "index";
	    	  }    	       
	      } else {
	        message = "?message=1";
	      }
	    } else {
	      message = "?message=0";
	    }
	    return url + message;
	  }
}
