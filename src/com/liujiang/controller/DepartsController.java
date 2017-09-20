package com.liujiang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.daibingjie.pojo.Departs;
import com.liujiang.service.DepartsService;

@Controller
public class DepartsController {
	
	@Resource(name="departsService")
	private DepartsService departsService;
	
	
	@RequestMapping("departsController")
	public String findAll(ModelMap modelMap){
		
		List<Departs> departs = departsService.findAll();
		modelMap.put("departs", departs);
		return "liujiang/dedo";
	}
	
	@RequestMapping("editdeparts")
	public ModelAndView update(@RequestParam(value="deid") Integer deid){
		
		Departs departs = departsService.findById(deid);
		
		ModelAndView mv = new ModelAndView();
	
		mv.setViewName("liujiang/editdeparts");
		mv.addObject("departs",departs);
		return mv;
	}
	
	@RequestMapping("editAdd")
	public String add(@RequestParam(value="deid") Integer deid,ModelMap modelMap){
		Departs departs = new Departs();
		if(deid !=0 && deid !=null){
			departs = departsService.findById(deid);
		}
		modelMap.put("departs", departs);
		return "liujiang/editAdd";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public Object save(
					   @RequestParam(value="deid") Integer deid,
					   @RequestParam(value="dename") String dename,
					   @RequestParam(value="intro") String intro,
					   @RequestParam(value="deexist") Integer deexist
					   ){
		Departs departs = new Departs(deid, dename, intro, deexist);
		if(departs.getDeid()==null){
			departsService.add(departs);
		}else{
			departsService.modifyAll(departs);
		}
		Map<String,String> map = new HashMap<String, String>();
		map.put("result", "success");
		return map;
	}
	
	@RequestMapping("departsState")
	@ResponseBody
	public Object update(@RequestParam(value="deid") Integer deid,@RequestParam(value="deexist") Integer deexist){
		int count = departsService.findDoc(deid);
		System.out.println(count);
		Map<String,String> map = new HashMap<String, String>();
		if(count <1){
			int i = departsService.update(deid, deexist);
			if(i>0){
				map.put("result", "ok");
			}else{
				map.put("result", "");
			}
		}
		return map;
	}
	@RequestMapping("departsState1")
	@ResponseBody
	public Object update1(@RequestParam(value="deid") Integer deid,@RequestParam(value="deexist") Integer deexist){
		Map<String,String> map = new HashMap<String, String>();
			int i = departsService.update(deid, deexist);
			if(i>0){
				map.put("result", "ok");
			}else{
				map.put("result", "");
			}
		return map;
	}
	
}
