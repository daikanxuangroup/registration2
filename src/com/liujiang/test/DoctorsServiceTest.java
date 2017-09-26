package com.liujiang.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daibingjie.pojo.Admins;
import com.liujiang.pojo.Doctors;
import com.liujiang.service.DoctorsService;

public class DoctorsServiceTest {
	
	private DoctorsService doctorsService;
	
	@Test
	public void findAll(){
		List<Doctors> doctors = doctorsService.findAll();
		for(Doctors doctors2:doctors){
			System.out.println(doctors2.getTitle()+" "+doctors2.getDeparts().getDename());
		}
	}
	
	@Test
	public void select1(){
		int count = doctorsService.select2(10003);
		System.out.println(count);
	}
	@Test
	public void update(){
		Doctors doctors = new Doctors();
		doctors.setDoid(10058);
		doctors.setDoexist(0);
		
		int count = doctorsService.updateState(10053, 0);
		if(count > 0){
			System.out.println(count);
			System.out.println("ok");
		}else{
			System.out.println("no");
			System.out.println(count);
		}
	}
	/*@Test
	public void addAdmins(){
		Admins admins = new Admins();
		admins.setAname("123456");
		int count = doctorsService.addAdmins(admins);
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}*/
	
	/*@Test
	public void addAdmins(){
		Admins admins = new Admins();
		admins.setAname("医生6");
		admins.setPwd("000000");
		admins.setState(2);
		admins.setAexist(1);
		int count = doctorsService.addAdmins(admins);
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}*/
	@Test
	public void modify(){
		Doctors doctors = new Doctors();
		doctors.setDoid(10003);
		doctors.setDoname("米乾");
		doctors.setTitle("铜锣湾扛把子");
		doctors.setDoexist(1);
		doctors.setBcost(500.00);
		doctors.setDeid(10001);
		int count = doctorsService.modify(doctors);
		
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}
	
	/*@Test
	public void add(){
		Doctors doctors = new Doctors();
		doctors.setTitle("终结者");
		doctors.setDoname("维鲁斯");
		doctors.setDeid(10007);
		doctors.setBcost(800.00);
		doctors.setDoexist(1);
		int count = doctorsService.add(doctors);
		if(count > 0){
			System.out.println("ok");
		}else{
			System.out.println("no");
		}
	}*/
	
	@Test
	public void findById(){
		Doctors doctors = doctorsService.findById(10001);
		System.out.println(doctors.getDoname());
	}
	
	@Test
	public void modfiyState(){

		int count = doctorsService.modifyState(10001, 0);
		System.out.println(count);
	}
	
	@Before
	public void init(){
		ApplicationContext ctx = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		doctorsService = ctx.getBean("doctorsService",DoctorsService.class);
	}

}
