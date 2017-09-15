package com.daibingjie.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.daibingjie.pojo.Cards;
import com.daibingjie.pojo.Doctors;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.History;
import com.daibingjie.pojo.Registration;
import com.daibingjie.service.DoctorBusService;

@Controller
@SessionAttributes(types=Integer.class,value={"doctors","state"})
public class doctorBusController  {
	
	@Resource(name="doctorBusService")
	public DoctorBusService doctorBusService;
	@RequestMapping("index")
	public String index(HttpServletRequest request,ModelMap modelMap,HttpSession session){
		/**
		 * 主页面  从登陆用户 中的Session中拿出来医生ID
		 */
/*		 HttpSession session = request.getSession();
		 Integer statee =  (Integer) session.getAttribute("state");*/
		 Doctors doctors= (Doctors) session.getAttribute("doctors");

//		Doctors doctorss= (Doctors) modelMap.get("doctors");	
		List<Registration> list = doctorBusService.findpat(doctors.getDoid());
		modelMap.put("reg", list);
		return "doctorBus/index";
		
	}
	
	
	@RequestMapping("past")
	public String past(@RequestParam("cid")Integer cid,ModelMap modelMap,HttpSession session){
		/**
		 * 历史病例信息，从Session 中拿出部门deid  
		 * 页面传过来诊疗卡CID
		 */	
		Doctors doctors= (Doctors) session.getAttribute("doctors");
//		Doctors doctors= (Doctors) modelMap.get("doctors");
		List<History> list= doctorBusService.findHis(cid, doctors.getDeid());
	
		modelMap.put("hist", list);
		return "doctorBus/past";
		
	}
	
	@RequestMapping("findprid")
	public String findprid(@RequestParam("prid")Integer prid ,ModelMap modelMap){		
		/**
		 * 查看某张药方项
		 */	
		List<Drugandprescripton> list= doctorBusService.findDrug(prid);

		modelMap.put("dplist", list);
		return "doctorBus/drugprs";
		
	}	

	
	@RequestMapping(value="stateprg",method=RequestMethod.POST)
	@ResponseBody
	public String staterig(@RequestParam("rid")Integer rid){
		/**
		 * 未来取号，修改订单状态的
		 */
		int conun =doctorBusService.updarig(rid, 2);
		return String.valueOf(conun);
		
	}
	@RequestMapping("xinx")
	public String xinx(@RequestParam("cid")Integer cid,ModelMap modelMap){
		Cards cards=doctorBusService.findcard(cid);
		modelMap.put("cards", cards);
		return "doctorBus/xinx";
		
	}


	
	
	
	
	
	
	
}
