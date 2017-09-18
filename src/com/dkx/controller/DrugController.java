package com.dkx.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daibingjie.aop.AuthPassport;
import com.dkx.pojo.Departs;
import com.dkx.pojo.Drug;
import com.dkx.pojo.Drugtype;
import com.dkx.service.DrugService;

@Controller
public class DrugController {
	
	@Resource(name = "drugService")
	private  DrugService service;
	
	@AuthPassport
	@RequestMapping("findDrug")
	public String findAll(@RequestParam(value="price1",required=false)Double price1,@RequestParam(value="price2",required=false)Double price2,
			ModelMap modelMap){
		List<Drug> list = service.findAll(price1, price2);
		modelMap.put("price1", price1);
		modelMap.put("price2", price2);
		modelMap.put("drlist", list);
		return "burgBus/drug-list";
	}
	
	@AuthPassport
	@RequestMapping("drugState")
	@ResponseBody
	public Object drayState(@RequestParam(value="drid")Integer drid,@RequestParam(value="drstate")Integer drstate){
		int i = service.updateDrugState(drid, drstate);
		Map<String,String> map = new HashMap<String, String>();
		if(i>0){
			map.put("result", "ok");
		}else
			map.put("result", "");
		return map;
	}
	
	@AuthPassport
	@RequestMapping("editDrug")
	public String addOrEdit(@RequestParam(value="drid")Integer drid,ModelMap modelMap){
		Drug drug = new Drug();
		System.out.println("drid================"+drid);
		List<Departs> delist=service.findDep();
		System.out.println(delist.size());
		if(drid!=0&&drid!=null){
			drug = service.findById(drid);
		}
		List<Drugtype> dylist = service.findAllDy();
		List<Departs> drdelist = service.findDrDe(drid);
		modelMap.put("delist", delist); //所有在用科室
		modelMap.put("dr", drug); //要修改的药
		modelMap.put("dylist", dylist); //所有在用类型
		modelMap.put("drdelist", drdelist); //用该药的科室
		System.out.println("--药名--"+drug.getDrname());
		return "burgBus/drug";
	}
	
	@AuthPassport
	@RequestMapping("editOver")
	@ResponseBody
	public Object editOver(@RequestParam(value="drid") Integer drid,
			@RequestParam(value="drstate") Integer drstate,
			@RequestParam(value="drname") String drname,
			@RequestParam(value="drsum") Integer drsum,
			@RequestParam(value="drprice") Double drprice,
			@RequestParam(value="dyid") Integer dyid,
			@RequestParam(value="deid") int[] deid){
		System.out.println("进入新增修改控制器");
		Drug drug = new Drug(drid, drname, drsum, drprice, drstate, dyid);
		System.out.println("-------->新增or修改----->"+drug);
		if(drug.getDrid()==null){
			service.addDrug(drug,deid);
		}else{
			//如果是修改，则要先删掉药品科室关系再添加
			service.modifyDrug(drug, deid);
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("result", "success");
		return map;
	}
}
