package com.liujiang.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daibingjie.pojo.Departs;
import com.liujiang.service.DepartsService;

public class DepartsServiceTest {

	private DepartsService departsService;
	
	
	@Test
	public void add(){
		Departs departs = new Departs();
		departs.setDename("..");
		departs.setDeexist(1);
		
		int count = departsService.add(departs);
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}
	@Test
	public void findDoc(){
		int count = departsService.findDoc(10003);
		System.out.println(count);
	}
	
	@Test
	public void modifyAll(){
		Departs departs = new Departs();
		departs.setDeid(10007);
		departs.setDename("脑科");
		departs.setIntro("11111");
		int count = departsService.modifyAll(departs);
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}
	@Test
	public void update(){
		int count = departsService.update(10001, 1);
		System.out.println(count);
	}
	
	@Test
	public void modify(){
		Departs departs = new Departs();
		departs.setDeid(10001);
		departs.setDeexist(0);
		
		int count = departsService.modify(departs);
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}
	
	@Test
	public void findById(){
		Departs departs = departsService.findById(10001);
		System.out.println(departs.getDename());
	}
	
	@Test
	public void findAll(){
		List<Departs> departs = departsService.findAll();
		for(Departs departs2:departs){
			System.out.println(departs2.getDename());
		}
	}
	
	@Before
	public void init(){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		departsService = ctx.getBean("departsService", DepartsService.class);
	}
}
