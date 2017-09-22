package com.dkx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.Drug;
import com.dkx.pojo.Drugtype;
import com.dkx.service.DrugService;
import com.dkx.service.StatisticsService;

import net.sf.json.JSONObject;

@Controller
public class StatisticsController {
	
	@Resource(name = "statisticsService")
	private StatisticsService serive;
	@Resource(name = "drugService")
	private  DrugService service;
	
	/**
	 * 医生门诊统计数据
	 * @param session
	 * @param response
	 */
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
	
	/**
	 * 进入门诊统计
	 * @return
	 */
	@AuthPassport
	@RequestMapping("docSta")
	public String patients(){
		System.out.println("返回页面");
		return "statisticsBus/chartsDoc";
	}
	/**
	 * 进入药品统计
	 * @return
	 */
	@AuthPassport
	@RequestMapping("drugSta")
	public String drugs(ModelMap modelMap){
		System.out.println("jinru页面");
		List<Drugtype> dylist = service.findUsedDy();
		modelMap.put("dylist", dylist);
		return "statisticsBus/chartsDrug";
	}
	/**
	 * 进入药品销量统计
	 * @return
	 */
	@AuthPassport
	@RequestMapping("drXsSta")
	public String drXsSta(ModelMap modelMap){
		System.out.println("进入页面");
		List<Drugtype> dylist = service.findAllDy();
		modelMap.put("dylist", dylist);
		return "statisticsBus/chartsDrSal";
	}
	
	/**
	 * 所有药品类型统计
	 */
	@AuthPassport
	@RequestMapping(value = "dtStatJson",produces = "application/json; charset=utf-8")
	@ResponseBody
	public void drugsType(HttpServletResponse response){
		List<Drugtype> atlist= serive.statTypes();
		Integer sum = 0;
		StringBuffer result1 = new StringBuffer("[");
		for (Drugtype dt : atlist) {
			String s = "[\""+dt.getDyname()+"\","+dt.getDystate()+"],";
			result1.append(s);
			sum += dt.getDystate();
		}
		if(atlist.size()>0)
			result1.deleteCharAt(result1.length() - 1); //去掉最后一个逗号
		
		result1.append("]");
		String result = "{\"atlist\":"+result1+",\"sum\":"+sum+"}";
//		String result = "{\"atlist\":[[\"抗生素类\",2],[\"中成药类\",2],[\"纱布\",0],[\"器具\",0],[\"注射用剂\",0],[\"麻醉剂\",0]],\"sum\":4}";
		System.out.println(result);
		try {
	        PrintWriter out = response.getWriter();
			out.println(result.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 所有药品类型销量统计
	 */
	@AuthPassport
	@RequestMapping(value = "tsalStatJson",produces = "application/json; charset=utf-8")
	@ResponseBody	
	public Object tsalStatJson(){
		List<String> mons = getMons();//半年时间
		Collections.reverse(mons);
		List<Drugtype> dts = service.findAllDy();
		List<Map<String, Object>> dt =new ArrayList<Map<String, Object>>();
		for (Drugtype d : dts) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Integer> sum = new ArrayList<Integer>();
			for (String mon : mons) {
				Drugtype dy = serive.statSalDt(d.getDyid(),mon);
				map.put("name", dy.getDyname());
				sum.add(dy.getBy2());//用by2装销量
			}
			map.put("data", sum);
			dt.add(map);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("month", mons);
		result.put("dt", dt);
		return result;
	}
	/**
	 * 某类药销售量统计
	 * @param dyid
	 * @param response
	 */
	@AuthPassport
	@RequestMapping(value = "dsalStatJson",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object dsalStatJson(@RequestParam(value="dyid") Integer dyid){
		List<String> mons = getMons();//半年时间
		Collections.reverse(mons);
		List<Drug> atlist = serive.statDrugs(dyid);
		List<Map<String, Object>> dt =new ArrayList<Map<String, Object>>();
		for (Drug d:atlist) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Integer> sum = new ArrayList<Integer>();
			for (String mon : mons){
				Drug dr = serive.statSalDr(d.getDrid(),mon);
				map.put("name", dr.getDrname());
				sum.add(dr.getBy2());//用by2装销量
			}
			map.put("data", sum);
			dt.add(map);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("month", mons);
		result.put("dt", dt);
		return result;
	}
	/**
	 * 某类药统计
	 * @param dyid
	 * @param response
	 */
	@AuthPassport
	@RequestMapping(value = "druStatJson",produces = "application/json; charset=utf-8")
	@ResponseBody
	public void druStatJson(@RequestParam(value="dyid") Integer dyid,HttpServletResponse response){
		List<Drug> atlist = serive.statDrugs(dyid);
		Integer sum = 0;
		StringBuffer result1 = new StringBuffer("[");
		for (Drug dr : atlist) {
			String s = "[\""+dr.getDrname()+"\","+dr.getDrsum()+"],";
			result1.append(s);
			sum += dr.getDrsum();
		}
		if(atlist.size()>0)
			result1.deleteCharAt(result1.length() - 1); //去掉最后一个逗号
		
		result1.append("]");
		String result = "{\"talist\":"+result1+",\"sum\":"+sum+"}";
//		String result = "{\"atlist\":[[\"抗生素类\",2],[\"中成药类\",2],[\"纱布\",0],[\"器具\",0],[\"注射用剂\",0],[\"麻醉剂\",0]],\"sum\":4}";
		System.out.println(result);
		try {
	        PrintWriter out = response.getWriter();
			out.println(result.toString());
	        out.flush();
	        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<String> getMons(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        List<String> mons = new ArrayList<String>();
        for(int i = 0;i<6;i++){
        	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - i); 
            date = calendar.getTime();
            String mon =  dateFormat.format(date);
            System.out.println(mon	);
            mons.add(mon);
        }
        return mons;
	}
	
}
