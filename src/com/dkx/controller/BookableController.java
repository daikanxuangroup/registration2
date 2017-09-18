package com.dkx.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.Bookable;
import com.daibingjie.pojo.Doctors;
import com.daibingjie.service.LoginService;
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
	@Resource(name = "loginService")
	private  LoginService lservice;
	
	
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
    
    /**
     * 进入排班管理
     * @param modelMap
     * @return
     */
    @AuthPassport
	@RequestMapping("gotoBK")
	public String findBK(ModelMap modelMap){
		
		System.out.println("准备排班");
		
		List<Departs> delist = dservice.findDep(); //科室
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String  datetime = dateFormat.format(new Date());
		modelMap.put("datetime", datetime);
		modelMap.put("delist", delist);
		return "bkbleBus/bookable2";
	}
	/*
	 * 查询科室所有医生的周排班情况----json版
	 */
    @AuthPassport
	@RequestMapping("findBK2")
	@ResponseBody
	public Object findBK(@RequestParam(value="deid")Integer deid,
			@RequestParam(value="datetime")String datetime){
		
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
		
		List<WeekBean> bklist = service.findBookable( list , deid); //排班
		
		boolean flag = false;//判断是否已排班
		for (WeekBean weekBean : bklist) {
			if(!StringUtils.isEmpty(addReg(weekBean).trim()))
				flag = true;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(flag)
			map.put("result", "yes");
		else
			map.put("result", "no");
		return map;
	}
	
	/*
	 * 查询科室所有医生的周排班情况
	 */
    @AuthPassport
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
		return "bkbleBus/bookable2";
	}
	
	//增加排班
    @AuthPassport
	@RequestMapping("addBK")
	public String addBK(@RequestParam(value="deid")Integer deid,
			@RequestParam(value="datetime")String datetime){
		List<Doctors> dl = service.findDoctors(deid);
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
		insertBK(dl, sdf, bklist);
		
		return "forward:/findBK.do";
	}
	
	/**
	 * 删除排班
	 * @param deid
	 * @param datetime
	 * @return
	 */
    @AuthPassport
	@RequestMapping("delBK")
	@ResponseBody
	public Object delBK(@RequestParam(value="doid")Integer doid,
			@RequestParam(value="datetime")String datetime){
		
		
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(datetime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		List<String> bklist = printWeekdays(calendar);//需删除排班的周日期list
		String afterDate = getDateAfter(7);//当前时间7天后的时间，也就是已经上线的可预约时间

		Doctors doct = lservice.findDeid(doid);
		Integer deid = doct.getDeid();//科室id
		
		String result = "";
		//如果所需删除时间全部在已上线时间前，禁止删除
		if(afterDate.compareTo(bklist.get(6))>=0){
			result = "before";
		}
		//如果所需删除时间都未上线则全部删除
		else if(afterDate.compareTo(bklist.get(0))<0){
			//医生列表
			List<Doctors> dolist = service.findDoctors(deid);
			System.out.println(dolist.size()+"存在的医生个数—————");
			//循环删除所选周内该科室所有医生的排班
			for (int i = 0; i < 7; i++) {
				for (Doctors d : dolist) {
					try {
						Date date1=sdf.parse(bklist.get(i));
						service.deleteBookable(d.getDoid(),date1);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			result = "after";
		}
		//删除周排班中有一部分已上线，则重排未上线部分
		else{
			//可用医生列表
			List<Doctors> dolist = service.findDoctors(deid);
			
			//判断哪天为可修改日期
			List<String> udlist = new ArrayList<String>();
			for (int i = 0; i < 7; i++) {
				if(afterDate.compareTo(bklist.get(i))<0){
					udlist.add(bklist.get(i));
				}
			}
			
			//删除未上线日期排班
			for (int i = 0; i < udlist.size(); i++) {
				
				for (Doctors d : dolist) {
					try {
						Date date1=sdf.parse(udlist.get(i));
						service.deleteBookable(d.getDoid(),date1);
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			insertBK(dolist, sdf, udlist);
			
			result = "center";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		map.put("deid", deid	);
		
		return map;
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
	
	//返回该医生一周是否值班
	private String addReg(WeekBean wk) {
		return wk.getAreg1()+wk.getAreg2()+wk.getAreg3()+wk.getAreg4()+wk.getAreg5()
		+wk.getAreg6()+wk.getAreg7()+wk.getPreg1()+wk.getPreg2()+wk.getPreg3()+wk.getPreg4()
		+wk.getPreg5()+wk.getPreg6()+wk.getPreg7();
	}
	
    /*
     * 当前日期N天后的时间
     */
    private String getDateAfter(int day){  
        Calendar now =Calendar.getInstance();  
        now.setTime(new Date());  
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = dateFormat.format(now.getTime());
        return s;  
     }  
}
