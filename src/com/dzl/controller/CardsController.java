package com.dzl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.daibingjie.aop.AuthPassport;
import com.dzl.pojo.Cards;
import com.dzl.service.CardsService;

@Controller
public class CardsController {
	@Resource(name = "cardsService")
	  private CardsService cardsService;
	
	
	@RequestMapping("addcard")
	@ResponseBody
	@AuthPassport
	public Map<String,String> addcards(Cards cards){
		cardsService.addCards(cards); 
		Map<String,String> map = new HashMap<String, String>();
		map.put("result", "success");
		return map;
	}
	
	@RequestMapping("cards")
	@AuthPassport
	public String ccards(){
		return "cards/addcard";
	}
	
	//String的替换，以达到保密效果
	private static String replaceAction(String str, String regular) {
        return str.replaceAll(regular, "*");
    }
	
	@RequestMapping("page")
	@AuthPassport
	public ModelAndView findByPage(@RequestParam(value="page",defaultValue="1") Integer pageNo,
			@RequestParam(value="rows",defaultValue="9999") Integer pageSize,
			@RequestParam(value="sort",defaultValue="cid") String  sort,
			@RequestParam(value="order",defaultValue="asc") String order,
			@RequestParam(value="pname",required=false) String pname
			) {
		List<Cards> list=cardsService.findByPage(pageNo, pageSize, sort, order, pname);
		int rows=cardsService.getTotal(pname);
		//过滤用户手机号及身份证
		Consumer<Cards> privacy = c -> {
			c.setPhone(replaceAction(c.getPhone(),"(?<=\\d{3})\\d(?=\\d{4})" ));
			c.setIdcard(replaceAction(c.getIdcard(), "(?<=\\d{4})\\d(?=\\d{3})" ));
		};
		list.forEach(privacy);
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("list",list);
		mv.addObject("rows",rows);
		mv.setViewName("cards/cardlist");
		return mv;
	}
	
	@RequestMapping("findbyid2222222222")
	@AuthPassport
	public ModelAndView findById(@RequestParam(value="idcard") Integer cid) {
		Cards cards=cardsService.findByIdcard(cid);
		ModelAndView mv=new ModelAndView();
		mv.addObject("cards",cards);
		mv.setViewName("ticket/updatecard");
		return mv;
	}
	
	@RequestMapping("updatecard")
	@ResponseBody
	@AuthPassport
	public Map<String,Integer> updateCard(@RequestParam(value="money") Double money,
		@RequestParam(value="cid") Integer cid) {
		Map<String,Integer> map = new HashMap<String, Integer>();
		if(money<0){
			map.put("result", 0);
			return map;
		}
		int rows=cardsService.updateRamaining(money, cid);
		map.put("result", rows);
		return map;
	}
	
}
