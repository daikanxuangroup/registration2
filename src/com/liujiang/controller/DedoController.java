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
import com.daibingjie.pojo.Doctors;
import com.liujiang.pojo.DepartsDoctors;
import com.liujiang.service.DedoService;

@Controller
public class DedoController {
	
	@Resource(name="dedoService")
	private DedoService dedoService;
	
	@RequestMapping("doctors-list")
	public String findAll(ModelMap modelMap){
		List<DepartsDoctors> doctors = dedoService.findDedo();
		modelMap.put("doctors", doctors);
		return "liujiang/doctors-list";
	}
	
	@RequestMapping("doctors-edit")
	public ModelAndView findById(@RequestParam(value="doid") Integer doid){
		
		/*DepartsDoctors doctors = new DepartsDoctors();*/
		Doctors doctors = new Doctors();
		List<Departs> departs = dedoService.findByDeid();
		if(doid !=0 && doid !=null){
			doctors = dedoService.find(doid);
		}
		
		ModelAndView mv = new ModelAndView();
		// System.out.println(doid+"*********");
		mv.setViewName("liujiang/doctors-edit");
		mv.addObject("doctors",doctors);
		mv.addObject("departs",departs);
		return mv;
	}
	@RequestMapping("doctors-save")
	@ResponseBody 
	public Object save(
			   @RequestParam(value="doid",required=false) Integer doid,
			   @RequestParam(value="doname",required=false) String doname,
			   @RequestParam(value="deid",required=false) Integer deid,
			   @RequestParam(value="info",required=false) String info,
			   @RequestParam(value="title",required=false) String title,
			   @RequestParam(value="bcost",required=false) Double bcost,
			   @RequestParam(value="doexist",required=false) Integer doexist/*,
			   @RequestParam(value="pcreg",required=false) Integer pcreg,
			   @RequestParam(value="xcreg",required=false) Integer xcreg,
			   
			   @RequestParam(value="monam",required=false) Integer monam,
			   @RequestParam(value="monpm",required=false) Integer monpm,
			   @RequestParam(value="tueam",required=false) Integer tueam,
			   @RequestParam(value="tuepm",required=false) Integer tuepm,
			   @RequestParam(value="wedam",required=false) Integer wedam,
			   @RequestParam(value="wedpm",required=false) Integer wedpm,
			   @RequestParam(value="thuam",required=false) Integer thuam,
			   @RequestParam(value="thupm",required=false) Integer thupm,
			   @RequestParam(value="friam",required=false) Integer friam,
			   @RequestParam(value="fripm",required=false) Integer fripm,
			   @RequestParam(value="satam",required=false) Integer satam,
			   @RequestParam(value="satpm",required=false) Integer satpm,
			   @RequestParam(value="sunap",required=false) Integer sunap,
			   @RequestParam(value="sumpm",required=false) Integer sumpm*/
			   ){
/*		System.out.println("进方法了");
		System.out.println("esimfsf"+deid+" "+title);
		Doctors doctors = new Doctors(doid, deid, doname, title, doexist, bcost);
		doctors.setDoid(doid);
		doctors.setTitle(title);
		doctors.setDoname(doname);
		doctors.setDoexist(doexist);
		doctors.setBcost(bcost);
		
			System.out.println(doctors.getDoid()+" "+doctors.getTitle());
			dedoService.modify(doctors);
		
		Map<String,String> map = new HashMap<String, String>();
		System.out.println(doctors.getDoid()+" "+doctors.getTitle());
		map.put("result", "success");*/
		return "";
	}
}
