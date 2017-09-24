package com.dzl.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dzl.pojo.Cards;
import com.dzl.service.CardsService;

public class CardsTest {
	private CardsService service;
	
	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=ctx.getBean("cardsService",CardsService.class);
	}
	
	
	@Test
	public void testadd(){
		Cards cards=new Cards();
		cards.setSex("男");
		cards.setDoexist(1);
		cards.setIdcard("430981199108155453");
		cards.setPhone("18153312454");
		cards.setPname("小家家");
		cards.setPwd("123456");
		cards.setRamaining(500.00);
		service.addCards(cards);
	}
	
	@Test
	public void testupdate(){
		int rows=service.updateRamaining(400.00, 1);
		System.out.println(rows);
	}
	
	@Test
	public void testfindbyidcard(){
		Cards cards=service.findByIdcard(1);
		System.out.println(cards.toString());
	}
	
	@Test
	public void testfindbypage(){
		List<Cards> list=service.findByPage(1, 5, "cid", "asc", null);
		for (Cards cards : list) {
			System.out.println(cards.toString());
		}
	}
	
	@Test
	public void testgettotal(){
		int rows=service.getTotal(null);
		System.out.println(rows);
	}
	@Test
	public void teststop(){
		int rows=service.stop("430981199108155456");
		System.out.println(rows);
	}
	
	@Test
	public void testbegin(){
		int rows=service.begin("430981199108155456");
		System.out.println(rows);
	}
}
