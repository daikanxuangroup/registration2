package com.dzl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.Departs;
import com.dzl.pojo.Bookable;
import com.dzl.pojo.Books;
import com.dzl.pojo.Cards;
import com.dzl.service.BooksService;
import com.dzl.service.CardsService;

@Controller
public class BooksConroller {
	@Resource(name = "booksService")
	private BooksService booksService;
	@Resource(name = "cardsService")
	private CardsService cardsService;
	
	@RequestMapping("findAll")
	@AuthPassport
	public ModelAndView findAll(@RequestParam(value="idcard",required=false)String idcard) throws ParseException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd mm:HH:ss");
		String a= sdf.format(date);
		String c=a+" 00:12:00";
		Date date2=sdf1.parse(c);
		List<Books> list=null;
		if(StringUtils.isEmpty(idcard))
			idcard = null;
		if((int)date.getTime()<=(int)date2.getTime()){
			list=booksService.findAll(-1,idcard);
		}else {
			list=booksService.findAll(1,idcard);
		}
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",list);
		mv.addObject("idcard",idcard);
		mv.setViewName("ticket/bookslist");
		return mv;
	}
	
	
	@RequestMapping("findMessage")
	@AuthPassport
	public ModelAndView findMessage(){
		HashMap<Departs, List<Doctors>> map=booksService.findMessage();
		ModelAndView mv=new ModelAndView();
		mv.addObject("map",map);
		mv.setViewName("ticket/message");
		return mv;
	}
	
	
	@AuthPassport
	@RequestMapping("findByDoname")
	public ModelAndView findByDoname(@RequestParam(value="doid") Integer doid) throws ParseException{
		Bookable bookable=booksService.findBookable(doid);
		ModelAndView mv=new ModelAndView();
		mv.addObject("bookable",bookable);
		mv.setViewName("ticket/bookablemessage");
		return mv;
	}
	
	@AuthPassport
	@RequestMapping("addticket")
	@ResponseBody
	public Object addticket(@RequestParam(value="medcard") Integer medcard,
			@RequestParam(value="bid") Integer bid,
			@RequestParam(value="doname") String  doname,
			@RequestParam(value="dename") String  dename,
			@RequestParam(value="bcost") Double bcost
			) throws ParseException{
		
		String pname=booksService.findPname(medcard);
		Map<String,Object> map = new HashMap<String, Object>();
		Bookable bookable=new Bookable();
		if(StringUtils.isEmpty(pname)){
//			bookable.setPname("该用户不存在");
			map.put("result", "nocard");
			return map;
		}
//		int snum=booksService.getSnum();
		bookable.setBid(bid);
		bookable.setDename(dename);
		bookable.setDoname(doname);
		bookable.setPname(pname);
		bookable.setMedcard(medcard);
//		bookable.setSnum(snum+1);
		bookable.setBcost(bcost);
		System.out.println(bookable.toString());
		map.put("books",bookable );
//		ModelAndView mv=new ModelAndView();
//		mv.addObject("books",bookable);
//		mv.setViewName("ticket/viewticket");
		map.put("result", "ok");
		return map;	
	}
	
	@AuthPassport
	@RequestMapping("addticket2")
	public ModelAndView addticket2(@RequestParam(value="medcard") Integer medcard,
			@RequestParam(value="bid") Integer bid,
			@RequestParam(value="doname") String  doname,
			@RequestParam(value="dename") String  dename,
			@RequestParam(value="bcost") Double bcost,
			@RequestParam(value="pname") String  pname
			) throws ParseException{
		
		Bookable bookable=new Bookable();
		//获得票号
		int snum=booksService.getSnum(bid);
		bookable.setBid(bid);
		bookable.setDename(dename);
		bookable.setDoname(doname);
		bookable.setPname(pname);
		bookable.setMedcard(medcard);
		bookable.setSnum(snum+1);
		bookable.setBcost(bcost);
		System.out.println(bookable.toString());
		ModelAndView mv=new ModelAndView();
		mv.addObject("books",bookable);
		mv.setViewName("ticket/viewticket");
		return mv;	
	}
	
		@AuthPassport
		@RequestMapping("viewticket")
		@ResponseBody
		public Object  viewticket(@RequestParam(value="red") Integer red,
				@RequestParam(value="card") Integer card,
				ModelMap modelMap) throws ParseException{
			System.out.println("------------------------------------");
			Map<String, Object> map = new HashMap<String, Object>();
			if(booksService.bdCard(red,card)>0){
				map.put("result", "okcard");
				return map;
			}else{
				map.put("result", "nocard");
				return map;
			}
		}
		
		@AuthPassport
		@RequestMapping("viewticket2")
		public Object  viewticket2(@RequestParam(value="red") Integer red,
				@RequestParam(value="card") Integer card,
				ModelMap modelMap) throws ParseException{
			System.out.println("--------2---------2------------2-------");
			Books books=booksService.findById(red);
			Integer bid = booksService.findbid(red);//找排班表
			//获得票号
			int snum=booksService.getSnum(bid);
			books.setSnum(snum+1);
			//绑定诊疗卡号
			books.setMedcard(card);
			modelMap.put("books",books);
			return "ticket/addticket";
		}
		
		@AuthPassport
		@RequestMapping("getticket")
		@ResponseBody
		public Map<String,String> getTicket(Books books){
//			if(books.getMedcard()!=null){
//			//诊疗卡扣费
//			cardsService.updateRamaining2(books.getBcost(), books.getMedcard());
//			}
//			//取票，修改状态，取票后该记录不再显示
//			booksService.addticket(books.getMedcard(), books.getBid(), books.getSnum());
//			booksService.updateReg(books.getRed());
//			Map<String,String> map = new HashMap<String, String>();
//			map.put("result", "success");
//			return map;
			
			Map<String,String> map = new HashMap<String, String>();
			//是否诊疗卡付款
			//用starttime装付款方式
			if(books.getStarttime()!=0){
				//诊疗卡扣费
				Cards cd = cardsService.findByIdcard(books.getMedcard());
				if(cd.getRamaining()<books.getBcost()){
					map.put("result", "little");
					return map;
				}	
				//扣费取号其实应该写在一个事务里，遵循事务的原子性
				cardsService.updateRamaining2(books.getBcost(), books.getMedcard());
			}
			//取票，修改状态，取票后该记录不再显示 
			booksService.addticket2(books.getMedcard(), books.getBid(), books.getSnum(),books.getRed());
				
			map.put("result", "success");
			return map;
		}
		
		@AuthPassport
		@RequestMapping("getticket2")
		@ResponseBody
		public Map<String,String> getTicket2(Books books){
			System.out.println(books);
			Map<String,String> map = new HashMap<String, String>();
			//是否诊疗卡付款
			if(books.getRed()!=0){
				//诊疗卡扣费
				Cards cd = cardsService.findByIdcard(books.getMedcard());
				if(cd.getRamaining()<books.getBcost()){
					map.put("result", "little");
					return map;
				}	
				cardsService.updateRamaining2(books.getBcost(), books.getMedcard());
			}
			//取票，修改状态
			booksService.addticket(books.getMedcard(), books.getBid(), books.getSnum());
				
			map.put("result", "success");
			return map;
		}
		
		
		//获得所有部门及部门所有挂号人数信息
		@RequestMapping("getall")
		public ModelAndView getall( ){
			Map<String,List<Integer>> map = new HashMap<String, List<Integer>>();
			List<String > list=booksService.getDename();
			
			
					for (String dename : list) {
						int count5=booksService.getCount5(dename);
						int count4=booksService.getCount4(dename);
						int count3=booksService.getCount3(dename);
						int count2=booksService.getCount2(dename);
						int count1=booksService.getCount1(dename);
						ArrayList<Integer> alist = new ArrayList<Integer>();
						alist.add(count1);
						alist.add(count2);
						alist.add(count3);
						alist.add(count4);
						alist.add(count5);
						map.put(dename, alist);
					}
			
					List<String> times = new ArrayList<String>();
					times.add("今天");
					times.add("昨天");
					times.add("本周");
					times.add("本月");
					times.add("本季度");
					int allno=booksService.getAllno(); //总数
					System.out.println(map.toString());
					ModelAndView mv=new ModelAndView();
					mv.addObject("allno",allno);
					mv.addObject("times",times);
					mv.addObject("map",map);
					mv.setViewName("ticket/welcome");
					return mv;

		}
		
}
