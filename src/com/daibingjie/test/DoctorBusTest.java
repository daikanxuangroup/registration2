package com.daibingjie.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daibingjie.pojo.Admins;
import com.daibingjie.pojo.By2State;
import com.daibingjie.pojo.Cards;
import com.daibingjie.pojo.Doctors;
import com.daibingjie.pojo.Druganddeparts;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.History;
import com.daibingjie.pojo.Registration;
import com.daibingjie.service.AuthorityService;
import com.daibingjie.service.DoctorBusService;
import com.daibingjie.service.LoginService;

public class DoctorBusTest {
	private DoctorBusService doctorBusService;
	private LoginService loginService;
	private AuthorityService authorityService;
	
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		doctorBusService=ctx.getBean("doctorBusService",DoctorBusService.class);
		loginService=ctx.getBean("loginService",LoginService.class);
		authorityService=ctx.getBean("authorityService",AuthorityService.class);
		
	}
	
	@Test
	public void findAdmins(){
		List<Admins> list = authorityService.liatAadmin();
		for(Admins a:list){
			System.out.println(a.getAname());
		}
	}
	
	
	@Test
	public void findpat(){
		List<Registration> list = doctorBusService.findpat(1);
		System.out.println(list.size());
/*		for(Registration reg:list){
			System.out.println(reg.getBookable().getBid()+"  "+reg.getCards().getCid());
			System.out.println(reg.getSnum()+"  "+reg.getState());
		}*/
	}
	@Test
	public void findcrad(){
		// 查看病人
		Cards card = doctorBusService.findcard(1);
		System.out.println(card.getPname());		
	}
	@Test
	public void allPrescripton(){
		// 开药 拿到药方ID
/*		int count =doctorBusService.allPrescripton(1, 1,1);
		System.out.println("药方id"+count);	*/	
	}
	
	@Test
	public void allDrugpres(){
		
		int count =doctorBusService.alldrugpres(3, 10005, 1);	
		System.out.println("成功"+count);	
	}
	/*@Test
	public void findHis(){
		
		List<History> list =doctorBusService.findHis(1, 1);
		System.out.println(list.size());
		for(History h:list){
			System.out.println(h.getPrescripton().getPrid());
		}
		
		
	}*/
	@Test
	public void findDrug(){	
		
	 List<Drugandprescripton> list =doctorBusService.findDrug(10005);
	 
	 System.out.println(list.size());
	 for(Drugandprescripton d:list){
		 System.out.println(d.getDrnum());
	 }
		
	}
	
	@Test
	public void findDrug2(){	
		
		Map<Integer, Drugandprescripton> map =doctorBusService.findMap(10005);
		Iterator<Drugandprescripton> lt=map.values().iterator();
		while (lt.hasNext()) {
			
		System.out.println(lt.next().getDrug().getDrname());
		System.out.println(lt.next().getDrug().getDrprice());
		System.out.println(lt.next().getDrnum());
		System.out.println(lt.next().getSum());
			
		}
		
	}	
	
	@Test
	public void login (){
		By2State bystate=doctorBusService.findBystate(1);
		System.out.println(bystate.getState()+  +bystate.getBy2());
	}
	
	@Test
	public void Drugand (){
		List<Druganddeparts> drdep=doctorBusService.finddru(1,10d,60d);
		System.out.println(drdep.size());
		for(Druganddeparts d:drdep){
			System.out.println(d.getDrug().getDrugtype().getDyname());
		}
	}
	
	@Test
	public  void xx(){
		String  a ="123213213";
		StringBuffer sb=new StringBuffer(a);
		System.out.println(sb.reverse());
		
	}

	
	
	
	
	
	
	
}
