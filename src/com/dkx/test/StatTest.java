package com.dkx.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dkx.pojo.Drugtype;
import com.dkx.service.DrugService;
import com.dkx.service.StatisticsService;

import net.sf.json.JSONArray;



public class StatTest {

	private StatisticsService serive;
	private  DrugService service2;
	
	@Before
	public void init() {
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	    service2 = ctx.getBean("drugService",DrugService.class);
	    serive = ctx.getBean("statisticsService",StatisticsService.class);
	  }
	
	@Test
	public void Test(){
		System.out.println("sdasd");
	}
	
    @Test
    public void tsalStatJson(){
		List<String> mons = getMons();//半年时间
		Collections.reverse(mons);
		List<Drugtype> dts = service2.findAllDy();
		List<Map<String, Object>> dt =new ArrayList<Map<String, Object>>();
		for (Drugtype d : dts) {
			int dyid = d.getDyid();
			Map<String, Object> map = new HashMap<String, Object>();
			List<Integer> sum = new ArrayList<Integer>();
			for (String mon : mons) {
				System.out.println(mon);
				Drugtype dy = serive.statSalDt(dyid,mon);
				map.put("name", dy.getDyname());
				sum.add(dy.getBy2());//用by2装销量
			}
			map.put("data", sum);
			dt.add(map);
		}
		JSONArray jsa= JSONArray.fromObject(dt);
		System.out.println(jsa);
//		return dt;
	}
    
    private List<String> getMons(){
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
}
