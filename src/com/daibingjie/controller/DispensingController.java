package com.daibingjie.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.By2State;
import com.daibingjie.pojo.Cards;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.Prescripton;
import com.daibingjie.service.DispensingService;
import com.daibingjie.service.DoctorBusService;


@Controller
public class DispensingController {

	
	@Resource(name="dispensingService")
	private  DispensingService dispensingService;
	
	@Resource(name="doctorBusService")
	private  DoctorBusService doctorBusService;
	
	
	@RequestMapping("listPres")
	public String listPres(ModelMap modelMap){
		
		List<Prescripton> list =dispensingService.listPrescripton();
		modelMap.put("list", list);		
		return "dispensing/prescripton-list";
			
	}
	
	/*@AuthPassport*/
	@RequestMapping("presXin")
	public String finddrandpr(ModelMap modelMap,@RequestParam("prid")Integer prid ,
			@RequestParam("cid") Integer cid){
		/**
		 * 查看药方项
		 */	
		Cards cards= doctorBusService.findcard(cid);
		Map<Integer,Drugandprescripton> map=doctorBusService.findMap(prid );	
		double sum = 0;
		Iterator<Drugandprescripton> it = map.values().iterator();
		// 累加购物项集合每个购物项的小计价格
		while (it.hasNext()) {
			sum += it.next().getSum();		
		}
		BigDecimal bd=new BigDecimal(sum);
		sum=bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		modelMap.put("prid",prid);
		modelMap.put("sum", sum);
		modelMap.put("map", map);
		modelMap.put("cards",cards);
		return "dispensing/prescription";	
	}
	
	@RequestMapping("recharge")
	public String recharge(){
		
		return "dispensing/recharge";
		
	}
	
	
	@RequestMapping("addrecharge")
	@ResponseBody
	public String addrecharge(@RequestParam("cid") Integer cid , 
			@RequestParam("price") Double price){
		Cards cards= doctorBusService.findcard(cid);
		String data=null;
		if(cards!=null){
			dispensingService.updateCards(price, cid);
			data="ok";
		}else{
			data="卡号不正确";
		}
		return data;			
	}

	@RequestMapping("charge")
	@ResponseBody
	public String charge(ModelMap modelMap,@RequestParam("prid")Integer prid ,
			@RequestParam("cid") Integer cid , 
			@RequestParam("price") Double price){
		// 收费 业务流程	
		String data="no";
		if(0<dispensingService.charge(prid, cid, price)){
			data="true";
		}
		
		return data;
		
	}
	
}
