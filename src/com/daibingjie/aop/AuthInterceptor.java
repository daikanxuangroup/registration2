package com.daibingjie.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.daibingjie.pojo.By2State;
import com.daibingjie.pojo.Doctors;


public class AuthInterceptor  extends HandlerInterceptorAdapter{
									  
	 @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        
	        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){
	            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);
	            if(authPassport == null || authPassport.validate() == false)
	                return true;
	            else{
	                   //拦截代码块      
	            	HttpServletRequest req = (HttpServletRequest) request;
	            	HttpSession session = request.getSession();
	            	Integer state=(Integer) session.getAttribute("state");//拿出权限
	       
	            // 不为空进入	
	            if(state!=null){ 
	           
	            	// 如果是医生的
	            	if(state==2){
	            		// sission拿医生
	            		Doctors doctors=(Doctors) session.getAttribute("doctors");
	            
	            		//有session 医生信息
	            		if(doctors!=null && doctors.getDoid()!=null){
	            			return true;     			
	            	//没有session 没医生信息
	            		}else{	            			
	            			response.sendRedirect("/registration2/login.jsp");	            		
	            			return false;
	            		}
	                   				
	            	
	            	}else{
	            		return true;
	            	}
	            	         	
	            // 没权限跳登录
	            }else{
	            	
	            	response.sendRedirect("/registration2/login.jsp");
	            	return false;
	            }
	            	
	            }
	        }
	        else{
	        
	            return true;
	        }
		   
	     }

}
