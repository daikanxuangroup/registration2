package com.dkx.test;

import java.text.ParseException;
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

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daibingjie.pojo.Bookable;
import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.Drugtype;
import com.dkx.pojo.WeekBean;
import com.dkx.service.BookableService;
import com.dkx.service.DrugService;
import com.dkx.service.StatisticsService;

import net.sf.json.JSONObject;


public class BookableTest {

	private BookableService service;
/*	private  DrugService service2;
	private StatisticsService serive;*/
	
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=ctx.getBean("bookableService",BookableService.class);	
/*		serive = ctx.getBean("statisticsService",StatisticsService.class);
		service2 = ctx.getBean("drugService",DrugService.class);	*/
	}	
	
	@Test
	public void test(){
		System.out.println("ssssssssssssddd");
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
    
    @Test
    public void tsalStatJson(){
		List<String> mons = getMons();//半年时间
		Collections.reverse(mons);
		/*List<Drugtype> dts = service2.findAllDy();
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
		JSONObject jst = JSONObject.fromObject(dt);
		System.out.println(jst);*/
//		return dt;
	}
    
	/*
	 * 查询科室所有医生的周排班情况
	 */
    @Test
	public void findBK(){
		
		Integer deid = 1;
		String datetime = "2017-09-20";
		
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
		
		List<WeekBean> bklist = service.findBookable( list , deid); //排班
		
		boolean flag = false;//判断是否已排班
		for (WeekBean weekBean : bklist) {
			if(!StringUtils.isEmpty(addReg(weekBean).trim())){
				flag = true;
			}	
		}
		if(flag)
			System.out.println("yes");
		else
			System.out.println("no");
	}

    @Test
    public void addBK(){
    	Integer deid = 1;
    	List<Doctors> dl = service.findDoctors(deid);
    	dl.forEach((item)->{
    		System.out.println(item.getDoname());
    	});
    	String datetime = "2017-09-22";
    	//日期列表
    			SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    			Date date = new Date();
    			System.out.println("datetime:"+datetime+"进入添加排班！---------");
    			try {
    				date = sdf.parse(datetime);
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			Calendar calendar = Calendar.getInstance();
    			calendar.setTime(date);
    			List<String> bklist = printWeekdays(calendar);
    			System.out.println("----------");
    			bklist.forEach(System.out::println);
    			
    			insertBK(dl, sdf, bklist);
    }
    
    @Test
	public List<String> getMons(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        List<String> mons = new ArrayList<String>();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1); 
        for(int i = 0;i<6;i++){
        	calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); 
            date = calendar.getTime();
            String mon =  dateFormat.format(date);
            mons.add(mon);
        }
        return mons;
	}
    
	//依次插入星期X排班情况
	private void insertBK(List<Doctors> dolist,SimpleDateFormat sdf,List<String> bklist){
		Collections.reverse(bklist);
		System.out.println("dolist.size--"+dolist.size());
		for (Doctors doc : dolist) {

			int size = bklist.size();
			
			//判断周六上午是否上班
			if(doc.getSatam()>0 && size >0){
				try {
					Date date2=sdf.parse(bklist.get(0));
						Bookable bk = new Bookable();
						
						bk.setDoctors(doc); //排班医生
						bk.setBdate(date2); //排班日期
						bk.setBnum(doc.getPcreg()*4); //网上可预约数
						bk.setYnum(0);//网上已预约人数
						bk.setXcum(doc.getXcreg()*4);//现场可预约数
						bk.setXcyum(0);//现场已预约数量
						bk.setStarttime(-1); //上午
						bk.setUsed(0);
						service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周六下午是否上班
			if(doc.getSatpm()>0 && size >0){
				try {
					Date date2=sdf.parse(bklist.get(0));
						Bookable bk = new Bookable();
						
						bk.setDoctors(doc); //排班医生
						bk.setBdate(date2); //排班日期
						bk.setBnum(doc.getPcreg()*3); //网上可预约数
						bk.setYnum(0);//网上已预约人数
						bk.setXcum(doc.getXcreg()*3);//现场可预约数
						bk.setXcyum(0);//现场已预约数量
						bk.setStarttime(1); //下午
						bk.setUsed(0);
						service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			//判断周五上午是否上班
			if(doc.getFriam()>0 && size > 1){
				try {
					Date date2=sdf.parse(bklist.get(1));
						Bookable bk = new Bookable();

						bk.setDoctors(doc); //排班医生
						bk.setBdate(date2); //排班日期
						bk.setBnum(doc.getPcreg()*4); //网上可预约数
						bk.setYnum(0);//网上已预约人数
						bk.setXcum(doc.getXcreg()*4);//现场可预约数
						bk.setXcyum(0);//现场已预约数量
						bk.setStarttime(-1); //上午
						bk.setUsed(0);
						service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周五下午是否上班
			if(doc.getFripm()>0 && size >1){
				try {
					Date date2=sdf.parse(bklist.get(1));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*3); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*3);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(1); //下午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周四上午是否上班
			if(doc.getThuam()>0&& size >2){
				try {
					Date date2=sdf.parse(bklist.get(2));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*4); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*4);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(-1); //上午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周四下午是否上班
			if(doc.getThuam()>0&& size >2){
				try {
					Date date2=sdf.parse(bklist.get(2));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*3); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*3);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(1); //下午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周三上午是否上班
			if(doc.getWedam()>0&& size >3){
				try {
					Date date2=sdf.parse(bklist.get(3));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*4); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*4);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(-1); //上午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周三下午是否上班
			if(doc.getWedpm()>0&& size >3){
				try {
					Date date2=sdf.parse(bklist.get(3));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*3); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*3);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(1); //下午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			//判断周二上午是否上班
			if(doc.getTueam()>0&& size >4){
				try {
					Date date2=sdf.parse(bklist.get(4));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*4); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*4);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(-1); //上午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周二下午是否上班
			if(doc.getTuepm()>0&& size >4){
				try {
					Date date2=sdf.parse(bklist.get(4));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*3); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*3);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(1); //下午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周一上午是否上班
			if(doc.getMonam()>0&& size >5){
				try {
					Date date2=sdf.parse(bklist.get(5));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*4); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*4);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(-1); //上午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周一下午是否上班
			if(doc.getMonpm()>0&& size >5){
				try {
					Date date2=sdf.parse(bklist.get(5));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*3); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*3);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(1); //下午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			//判断周日上午是否上班
			if(doc.getSunap()>0&& size >6){
				try {
					Date date2=sdf.parse(bklist.get(6));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*4); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*4);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(-1); //上午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			
			//判断周日下午是否上班
			if(doc.getSumpm()>0&& size >6){
				try {
					Date date2=sdf.parse(bklist.get(6));
					Bookable bk = new Bookable();
					
					bk.setDoctors(doc); //排班医生
					bk.setBdate(date2); //排班日期
					bk.setBnum(doc.getPcreg()*3); //网上可预约数
					bk.setYnum(0);//网上已预约人数
					bk.setXcum(doc.getXcreg()*3);//现场可预约数
					bk.setXcyum(0);//现场已预约数量
					bk.setStarttime(1); //下午
					bk.setUsed(0);
					service.addBookable(bk);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}			
		}
	}	
	
	private String addReg(WeekBean wk) {
		return wk.getAreg1()+wk.getAreg2()+wk.getAreg3()+wk.getAreg4()+wk.getAreg5()
		+wk.getAreg6()+wk.getAreg7()+wk.getPreg1()+wk.getPreg2()+wk.getPreg3()+wk.getPreg4()
		+wk.getPreg5()+wk.getPreg6()+wk.getPreg7();
	}
}
