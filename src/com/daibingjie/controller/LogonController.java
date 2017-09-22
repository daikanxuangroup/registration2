package com.daibingjie.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.Doctors;
import com.daibingjie.service.LoginService;

import sun.security.action.PutAllAction;

@Controller
@SessionAttributes(types=Integer.class,value={"doctors","state","adm"})
public class LogonController {
	
	@Resource(name="loginService")
	public LoginService loginService;	
		/**
	   * 用户登录
	   */
	  @RequestMapping("/login")
	  public String login(@RequestParam(value="aname",required=false) String aname , @RequestParam(value="pwd",required=false) String pwd,
	      @RequestParam(value="verifyCode",required=false) String verifyCode, HttpServletRequest request,ModelMap modelMap) {
		  if(aname==null || pwd ==null || verifyCode==null){
		    	return "redirect:/login.jsp";
		    }	  		  
		 HttpSession session = request.getSession();
		 String securityCode = (String) session.getAttribute("securityCode");
		 String url = "redirect:/login.jsp";
		 String message = "";
	   
	    if (verifyCode.equals(securityCode)) {
	      
		Admins adm = loginService.find(aname, pwd);
	      if (adm != null) {
	    	  Integer state=adm.getState();
	    	  modelMap.put("state",state);// 用来判断用户的身份
	    	  modelMap.put("adm", adm);
	    	  url = "index";
	    	  if(adm.getState()==2 ||adm.getState()==0){
	    		session.setMaxInactiveInterval(60*60*2);
	    		Doctors doctors=loginService.findDeid(adm.getDoid());  
	    	
	    		modelMap.put("doctors", doctors);// 医生药用的    		
	    		 
	    	  }    	       
	      } else {
	        message = "?message=1";
	      }
	    } else {
	      message = "?message=0";
	    }
	    return url + message;
	  }
	  
	  @RequestMapping("/removeSesson")
	  public String  removeSesson(HttpSession session ){
		  session.removeAttribute("state");
		  session.removeAttribute("doctors");
		  
		return "redirect:/login.jsp";
		  
	  }
	  
		@AuthPassport
		@RequestMapping("/updapwd")
		@ResponseBody
	  public String updapwd(@RequestParam("pwd") String pwd,
			  @RequestParam("password") String password,
			  @RequestParam("password2") String password2,
			  HttpSession session,ModelMap modelMap){
		/*	修改密码的*/
			String mgs="false";
			Admins adm =(Admins) session.getAttribute("adm");
			if(0<loginService.updapwd(adm.getAname(), pwd,password)){
				adm = loginService.find(adm.getAname(), password);		// 从放入Session
				modelMap.put("adm", adm);
				mgs="true";
			}		
		return mgs;
		  
		  
	  }
	  
	  
	  
	  
	  
}
