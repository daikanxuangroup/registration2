package com.dzl.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.chainsaw.Main;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dzl.pojo.Bookable;
import com.dzl.pojo.Books;
import com.dzl.service.BooksService;

public class BooksServiceTest {
	private BooksService service;

	@Before
	public void init(){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		service=ctx.getBean("booksService",BooksService.class);
	}

	@Test
	public void testAll() throws ParseException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd mm:HH:ss");
		String a= sdf.format(date);
		String c=a+" 00:12:00";
		Date date2=sdf1.parse(c);
		List<Books> list=null;
		if((int)date.getTime()<=(int)date2.getTime()){
			list=service.findAll(-1);
		}else {
			list=service.findAll(1);
		}

		for (Books books : list) {
			System.out.println(books.toString());
		}
	}

	@Test
	public void testId(){
		Books books=service.findById(10001);
		System.out.println(books.toString());
	}

	@Test
	public void testSnum() throws ParseException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd mm:HH:ss");
		String a= sdf.format(date);
		String c=a+" 00:12:00";
		Date date2=sdf1.parse(c);
		System.out.println(date);
		System.out.println(date2);
	}

	@Test
	public void testMessage(){
		HashMap<String , List<String>> map=service.findMessage();

		Set<String > keySet=map.keySet();
		Iterator<String> keyIterator=keySet.iterator();
		while(keyIterator.hasNext()){
			String a=keyIterator.next();
			System.out.println(a);
			List<String > list=map.get(a);
			for (String string : list) {
				System.out.println(string);
			}
		}
	}
	
	@Test
	public void testdoname() throws ParseException{
		Bookable bookable=service.findBookable("张一");
		System.out.println(bookable.getXcum()+"  "+bookable.getXcyum()+"  "+bookable.getDename()+"  "+bookable.getDoname());
	}
	
	@Test
	public void testMedcard(){
		int medcard=service.findMedcard("430981199108155456");
		System.out.println(medcard);
	}
	
	@Test
	public void testID(){
		Books books=service.findById(10073);
		System.out.println(books.toString());
	}
	
	@Test
	public void testAdd(){
		int rows=service.addticket(10001, 10025, 1);
		System.out.println(rows);
		
	}
}


