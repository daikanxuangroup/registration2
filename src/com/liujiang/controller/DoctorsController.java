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

import com.liujiang.pojo.Departs;
import com.liujiang.pojo.Doctors;
import com.liujiang.service.DoctorsService;

@Controller
public class DoctorsController {
	
	@Resource(name="doctorsService")
	private DoctorsService doctorsService;
	
	// 查询所有医生信息
	@RequestMapping("doctors-list")
	public String findAll(ModelMap modelMap){
		
		System.out.println();
		List<Doctors> doctors = doctorsService.findAll();
		modelMap.put("doctors", doctors);
		return "doctors/doctors-list";
	}

	// 根据id查询医生信息
	@RequestMapping("doctors-edit")
	public String findById(@RequestParam(value="doid")Integer doid,ModelMap modelMap){
		Doctors doctors = new Doctors();
		List<Departs> dylist = doctorsService.findDep();
		
		System.out.println("size:"+dylist.size());
		System.out.println(doid);
		if(doid !=0 && doid !=null){
			doctors = doctorsService.findById(doid);
		}
		modelMap.put("dylist", dylist);
		modelMap.put("dr", doctors);
		
		return "doctors/doctors-edit";
	}
	@RequestMapping("doctorsState")
	@ResponseBody
	public Object updateState(@RequestParam(value="doid") Integer doid,@RequestParam(value="doexist") Integer doexist){
		int count1 = doctorsService.select1(doid);
		int count2 = doctorsService.select2(doid);
		System.out.println("select1 "+count1+" select2 "+count2);
		System.out.println(doexist);
		
		Map<String,String> map = new HashMap<String, String>();
		if(doexist !=1){
			if(count1 == 0 && count2 == 0){
				int count3 = doctorsService.updateState(doid, doexist);
				if(count3 > 0){
					map.put("result", "ok");
				}else{
					map.put("result", "");
				}			
			}else{
				map.put("result", "no");
			}
		}else{
			int count3 = doctorsService.updateState(doid, doexist);
			if(count3 > 0){
				map.put("result", "ok");
			}else{
				map.put("result", "");
			}	
		}
		
		return map;
	}
	
	// 添加或修改医生信息
	@RequestMapping("doctors-modifyAndadd")
	@ResponseBody
	public Object modifyAndadd(Doctors doctors,@RequestParam(value="aname",required=false)String aname){
		System.out.println("进方法");
		System.out.println(aname);
		
		System.out.println(doctors.getDoname()+"医生名字");
		System.out.println(doctors.getMonam()+"周一上午");
		System.out.println(doctors.getMonpm()+"周一下午");
		System.out.println(doctors.getTueam()+"周二上午");
		System.out.println(doctors.getTuepm()+"周二下午");
		if(doctors.getMonam()==null){
			doctors.setMonam(0);
		}if(doctors.getMonpm()==null){
			doctors.setMonpm(0);
			
		}if(doctors.getTueam()==null){
			doctors.setTueam(0);
		}if(doctors.getTuepm()==null){
			doctors.setTuepm(0);
			
		}if(doctors.getWedam()==null){
			doctors.setWedam(0);
		}if(doctors.getWedpm()==null){
			doctors.setWedpm(0);
			
		}if(doctors.getThuam()==null){
			doctors.setThuam(0);
		}if(doctors.getThupm()==null){
			doctors.setThupm(0);
			
		}if(doctors.getFriam()==null){
			doctors.setFriam(0);
		}if(doctors.getFripm()==null){
			doctors.setFripm(0);
			
		}if(doctors.getSatam()==null){
			doctors.setSatam(0);
		}if(doctors.getSatpm()==null){
			doctors.setSatpm(0);
			
		}if(doctors.getSunap()==null){
			doctors.setSunap(0);
			
		}if(doctors.getSumpm()==null){
			doctors.setSumpm(0);
		}
		Map<String,String> map = new HashMap<String, String>();
		if(doctors.getDoid()==null ){
			doctorsService.add(doctors,aname);
			map.put("add", "add");
		}else{
			doctorsService.modify(doctors);
			map.put("modify", "modify");
		}
		return map;
	}
}
