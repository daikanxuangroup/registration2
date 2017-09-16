package com.dkx.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.WeekBean;
import com.dkx.service.BookableService;


public class BookableTest {

	private BookableService service;
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=ctx.getBean("bookableService",BookableService.class);	
	}	
	
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd EE");
            String s = dateFormat.format(calendar.getTime());
            list.add(s);
            calendar.add(Calendar.DATE, 1);
        }
        return list;
    }
	/*
	 * 查询科室所有医生的周排班情况
	 */
    @Test
	public void findBK(){
		
		Integer deid = 1;
		String datetime = "2017-09-15";
		
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
		List<String> wklist = onlyWeek(calendar2);
		
		List<WeekBean> bklist = service.findBookable( list , deid);
		
		bklist.forEach(System.out :: println);
		wklist.forEach(System.out :: println);
		System.out.println("--------");
		list.forEach(System.out::println);
		System.out.println("----");
	}

    @Test
    public void addBK(){
    	Integer deid = 1;
    	List<Doctors> dl = service.findDoctors(deid);
    	dl.forEach(System.out::println);
    }
}
