package com.liujiang.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daibingjie.pojo.Departs;
import com.daibingjie.pojo.Doctors;
import com.liujiang.pojo.DepartsDoctors;
import com.liujiang.service.DedoService;

public class DedoServiceTest {
	
	private DedoService dedoService;
	
	
	@Test
	public void findDedo(){
		List<DepartsDoctors> doctors = dedoService.findDedo();
		for(DepartsDoctors doctors2 : doctors){
			System.out.println(doctors2.getDename()+" "+doctors2.getDoname());
		}
	}
	
	/*@Test
	public void findById(){
		DepartsDoctors doctors = dedoService.findById(10001);
		System.out.println(doctors.getDoname());
	}*/
	
	@Test
	public void findByDeid(){
		List<Departs> departs = dedoService.findByDeid();
		for(Departs departs2:departs){
			System.out.println(departs2.getDename());
		}
	}
	
	@Test
	public void modify(){
		Doctors doctors = new Doctors();
		doctors.setDoid(10002);
		doctors.setDeid(10001);
		doctors.setDoname("肖敏2");
		doctors.setTitle("1111");
		doctors.setDoexist(1);
		doctors.setBcost(310.00);
		
		int count = dedoService.modify(doctors);
		if(count >0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}
	
	@Before
	public void init(){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		dedoService = ctx.getBean("dedoService",DedoService.class);
	}

}
