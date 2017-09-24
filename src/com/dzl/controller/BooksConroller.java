package com.dzl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView findAll() throws ParseException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd mm:HH:ss");
		String a= sdf.format(date);
		String c=a+" 00:12:00";
		Date date2=sdf1.parse(c);
		List<Books> list=null;
		if((int)date.getTime()<=(int)date2.getTime()){
			list=booksService.findAll(-1);
		}else {
			list=booksService.findAll(1);
		}
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",list);
		mv.setViewName("ticket/bookslist");
		return mv;
	}
	
	
	@RequestMapping("findMessage")
	public ModelAndView findMessage(){
		HashMap<Departs, List<Doctors>> map=booksService.findMessage();
		ModelAndView mv=new ModelAndView();
		mv.addObject("map",map);
		mv.setViewName("ticket/message");
		return mv;
	}
	
	
	
	@RequestMapping("findByDoname")
	public ModelAndView findByDoname(@RequestParam(value="doid") Integer doid) throws ParseException{
		Bookable bookable=booksService.findBookable(doid);
		ModelAndView mv=new ModelAndView();
		mv.addObject("bookable",bookable);
		mv.setViewName("ticket/bookablemessage");
		return mv;
	}
	
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
		int snum=booksService.getSnum();
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
	
		@RequestMapping("viewticket")
		public String  viewticket(@RequestParam(value="red") Integer red,ModelMap modelMap) throws ParseException{
			Books books=booksService.findById(red);
			//获得票号
			int snum=booksService.getSnum();
			books.setSnum(snum+1);
			//绑定诊疗卡号
			String idcard=books.getIdcard();
			int medcard=booksService.findMedcard(idcard);
			if(medcard!=0){
				books.setMedcard(medcard);
			}
			modelMap.put("books",books);
			return "ticket/addticket";
		}
		
		@RequestMapping("getticket")
		@ResponseBody
		public Map<String,String> getTicket(Books books){
			if(books.getMedcard()!=null){
			//诊疗卡扣费
			cardsService.updateRamaining2(books.getBcost(), books.getMedcard());
			}
			//取票，修改状态，取票后该记录不再显示
			booksService.addticket(books.getMedcard(), books.getBid(), books.getSnum());
			booksService.updateReg(books.getRed());
			Map<String,String> map = new HashMap<String, String>();
			map.put("result", "success");
			return map;
		}
		
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
			//取票，修改状态，取票后该记录不再显示
			booksService.addticket(books.getMedcard(), books.getBid(), books.getSnum());
				
			map.put("result", "success");
			return map;
		}
		
		
		
}
