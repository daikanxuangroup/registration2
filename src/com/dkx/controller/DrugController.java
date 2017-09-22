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
	
	//找药
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
	
	//改药品状态
	@AuthPassport
	@RequestMapping("drugState")
	@ResponseBody
	public Object drayState(@RequestParam(value="drid")Integer drid,@RequestParam(value="drstate")Integer drstate){
		Map<String,String> map = new HashMap<String, String>();
		int dystate = service.ckDyState(drid);
		if(dystate==0&&drstate==1){
			map.put("result", "stop");
			return map;
		}
		int i = service.updateDrugState(drid, drstate);
		
		if(i>0){
			map.put("result", "ok");
		}else
			map.put("result", "");
		return map;
	}
	
	//改类别状态
	@AuthPassport
	@RequestMapping("typeState")
	@ResponseBody
	public Object typeState(@RequestParam(value="dyid")Integer dyid,@RequestParam(value="dystate")Integer dystate){
		int drugnum = service.useDrByTp(dyid);
		int i = -1;
		Map<String,String> map = new HashMap<String, String>();
		
		//类型中还有可用药品，无法禁用
		if(drugnum>0&&dystate==0){
			map.put("result", "stop");
			return map;
		}
		i = service.updateTypeState(dyid, dystate);
		
		if(i>0){
			map.put("result", "ok");
		}else
			map.put("result", "");
		return map;
	}
	
	//进入新增或修改页面
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
		List<Drugtype> dylist = service.findUsedDy();
		List<Departs> drdelist = service.findDrDe(drid);
		modelMap.put("delist", delist); //所有在用科室
		modelMap.put("dr", drug); //要修改的药
		modelMap.put("dylist", dylist); //所有在用类型
		modelMap.put("drdelist", drdelist); //用该药的科室
		System.out.println("--药名--"+drug.getDrname());
		return "burgBus/drug";
	}
	
	//新增类型
	@AuthPassport
	@RequestMapping("typeAdd")
	@ResponseBody
	public Drugtype TypeAdd(@RequestParam(value="dyname") String dyname){
		System.out.println("新增类型");
		int dyid = service.addType(dyname);
		
		Drugtype dt = new Drugtype();
		dt.setDyid(dyid);
		dt.setDyname(dyname);
		return dt;
	}
	
	//新增or修改完毕
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
	
	//查类别
	@AuthPassport
	@RequestMapping("findType")
	public String findType(ModelMap modelMap){
		List<Drugtype> dtlist = service.findAllDy();
		modelMap.put("dtlist", dtlist);
		
		return "burgBus/drugtype";
	}
}
