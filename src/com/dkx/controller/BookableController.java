package com.dkx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dkx.pojo.Departs;
import com.dkx.pojo.WeekBean;
import com.dkx.service.BookableService;
import com.dkx.service.DrugService;

@Controller
public class BookableController {

	@Resource(name = "bookableService")
	private  BookableService service;
	@Resource(name = "drugService")
	private  DrugService dservice;
	
	//设置一周第一天为星期天
	private static final int FIRST_DAY = Calendar.SUNDAY;
    
    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }
    private static List<String> printWeekdays(Calendar calendar) {
    	List<String> list = new ArrayList<String>();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String s = dateFormat.format(calendar.getTime());
            list.add(s);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
    private static List<String> onlyWeek(Calendar calendar) {
    	List<String> list = new ArrayList<String>();
        setToFirstDay(calendar);
        for (int i = 0; i < 7; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  EE");
            String s = dateFormat.format(calendar.getTime());
            list.add(s);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
	/*
	 * 查询科室所有医生的周排班情况
	 */
	@RequestMapping("findBK")
	public String findBK(@RequestParam(value="deid")Integer deid,
			@RequestParam(value="datetime")String datetime,
			ModelMap modelMap){
		
		System.out.println("查询排班");
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println("datetime:"+datetime);
		try {
			date = sdf.parse(datetime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date);
		List<String> list = printWeekdays(calendar);
		
		List<Departs> delist = dservice.findDep(); //科室
		List<WeekBean> bklist = service.findBookable( list , deid); //排班
		List<String> wklist = onlyWeek(calendar2); //日期_列名
		modelMap.put("deid", deid);
		modelMap.put("datetime", datetime);
		modelMap.put("delist", delist);
		modelMap.put("bklist", bklist);
		modelMap.put("wklist", wklist);
		return "bkbleBus/bookable";
	}
	
	@RequestMapping("gotoBK")
	public String findBK(ModelMap modelMap){
		
		System.out.println("准备排班");
		
		List<Departs> delist = dservice.findDep(); //科室
		modelMap.put("delist", delist);
		return "bkbleBus/bookable";
	}
}
