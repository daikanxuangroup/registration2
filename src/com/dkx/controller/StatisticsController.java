package com.dkx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.Doctors;
import com.dkx.service.StatisticsService;

import net.sf.json.JSONObject;

@Controller
public class StatisticsController {
	
	@Resource(name = "statisticsService")
	private StatisticsService serive;
	
	@AuthPassport
	@RequestMapping("dstatjson")
	@ResponseBody
	public void patients(HttpSession session,HttpServletResponse response){
		Doctors doc =  (Doctors) session.getAttribute("doctors");
		Integer doid = doc.getDoid();
		List<Integer> alist = serive.findAllP(doid);
		List<Integer> dlist = serive.findDruP(doid);
		List<Integer> homlist = serive.findHomP(doid);
		List<Integer> hoslist = serive.findHosP(doid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alist", alist);
		map.put("dlist", dlist);
		map.put("homlist", homlist);
		map.put("hoslist", hoslist);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
        System.out.println("输出的结果是：" + jsonObject);
        //将json对象转化为json字符串
        String result = jsonObject.toString();
        System.out.println(result);
        
		try {
	        PrintWriter out = response.getWriter();
			out.println(result);
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@AuthPassport
	@RequestMapping("docSta")
	public String patients(){
		System.out.println("返回页面");
		return "statisticsBus/chartsDoc";
	}
}
