package com.daibingjie.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.daibingjie.aop.AuthPassport;
import com.daibingjie.pojo.By2State;
import com.daibingjie.pojo.Cards;
import com.daibingjie.pojo.Doctors;
import com.daibingjie.pojo.Druganddeparts;
import com.daibingjie.pojo.Drugandprescripton;
import com.daibingjie.pojo.History;
import com.daibingjie.pojo.Registration;
import com.daibingjie.service.DoctorBusService;


@AuthPassport
@Controller
@SessionAttributes(types=Integer.class,value={"doctors","state","bs","cards","adm"})
public class DoctorBusController  {
	
	@Resource(name="doctorBusService")
	public DoctorBusService doctorBusService;
	
	
	@AuthPassport
	@RequestMapping("index")
	public String index(HttpServletRequest request,ModelMap modelMap,HttpSession session){
		/**
		 * 主页面  从登陆用户 中的Session中拿出来医生ID
		 */
/*		 HttpSession session = request.getSession();
		 Integer statee =  (Integer) session.getAttribute("state");*/
		 Doctors doctors= (Doctors) session.getAttribute("doctors");

//		Doctors doctorss= (Doctors) modelMap.get("doctors");	
		List<Registration> list = doctorBusService.findpat(doctors.getDoid());
		modelMap.put("reg", list);
		return "doctorBus/index";
		
	}
	@AuthPassport
	@RequestMapping("past")
	public String past(@RequestParam("cid")Integer cid,ModelMap modelMap,HttpSession session){
		/**
		 * 历史病例信息，从Session 中拿出部门deid  
		 * 页面传过来诊疗卡CID
		 */	
		Doctors doctors= (Doctors) session.getAttribute("doctors");
//		Doctors doctors= (Doctors) modelMap.get("doctors");
		List<History> list= doctorBusService.findHis(cid, doctors.getDeid());
	
		modelMap.put("hist", list);
		return "doctorBus/past";
		
	}
	
	@AuthPassport
	@RequestMapping("findprid")
	public String findprid(@RequestParam("prid")Integer prid ,ModelMap modelMap){		
		/**
		 * 查看某张药方项
		 */	
		List<Drugandprescripton> list= doctorBusService.findDrug(prid);

		modelMap.put("dplist", list);
		return "doctorBus/drugprs";
		
	}	

	@AuthPassport
	@RequestMapping(value="stateprg",method=RequestMethod.POST)
	@ResponseBody
	public String staterig(@RequestParam("rid")Integer rid){
		/**
		 * 未来取号，修改订单状态的
		 */
		int conun =doctorBusService.updarig(rid, 2);
		return String.valueOf(conun);
		
	}
	
	@AuthPassport
	@RequestMapping("xinx")
	public String xinx(@RequestParam("cid")Integer cid,
			@RequestParam("rid")Integer rid,
			ModelMap modelMap,HttpSession session){
		
		By2State bs=doctorBusService.findBystate(rid);
		
		Cards cards=doctorBusService.findcard(cid);
		modelMap.put("cards", cards);
		modelMap.put("rid", rid);
		modelMap.put("bs", bs);
		return "doctorBus/xinx";
		
	}
	
	@AuthPassport
	@RequestMapping("drug")
	public String drug(@RequestParam(
			value="price1",required=false)Double price1,
			@RequestParam(value="price2",required=false)Double price2,
			@RequestParam("cid")Integer cid,@RequestParam("rid")Integer rid,
			ModelMap modelMap,HttpSession session){
		/**
		 * 转药品页面
		 */
		Doctors doctors= (Doctors) session.getAttribute("doctors");
		List<Druganddeparts> drlist= doctorBusService.finddru(doctors.getDeid(),price1,price2);
		modelMap.put("price1", price1);
		modelMap.put("price2", price2);
		modelMap.put("drlist", drlist);
		modelMap.put("rid", rid);
		return "doctorBus/drug";
		
	}
	
	@AuthPassport
	@RequestMapping("drandpr")
	@ResponseBody
	public String drandpr(ModelMap modelMap,
			HttpSession session,
			@RequestParam(value="drid")Integer drid
			,@RequestParam("rid")Integer rid){
		/**
		 * 诊疗卡id
		 * 页面拿到订单rid
		 * 是否有没有处理完的药方单
		 * 药品ID
		 */
		// 拿到诊疗by2     如果有值就是 今天的 药方ID	
	
		By2State bs =(By2State) session.getAttribute("bs");
		Integer by2state=bs.getBy2();
		String mgs="false";
		// 如果没有 就新增  药方
		if(by2state<100 || by2state==null){
			// 拿出医生对象
			Doctors doctors= (Doctors) session.getAttribute("doctors");
			// 创建药方添加 药方项 
			by2state=doctorBusService.allPrescripton(1, doctors.getDoid(), drid);
			// 修改状态 
			if(0<doctorBusService.updaby2(rid, by2state)){
		
				 bs=doctorBusService.findBystate(rid);
				 modelMap.put("bs", bs);

				// 是否Ajax
				mgs="true";
			}		
			
		}else{
			Map<Integer, Drugandprescripton> map=doctorBusService.findMap(by2state);
			Drugandprescripton temp = map.get(drid);
			// 没有就创建 新的订单项
			if(temp ==null ){
				if(0<doctorBusService.alldrugpres(drid, by2state, 1)){
					// 是否用Ajax 
				
					mgs="true";
				}
			}else{
				// 有就把数量+1
				int sum = temp.getDrnum()+1;
				if(0<doctorBusService.updatedrug(sum, drid, by2state)){
					// 是否用Ajax 
					mgs="true";
				}
			}			
		}
		return mgs;		
	}	
	
	@AuthPassport
	@RequestMapping("finddrandpr")
	public String finddrandpr(HttpSession session,
			ModelMap modelMap){
		/**
		 * 查看药方项
		 */
		
		By2State bs =(By2State) session.getAttribute("bs");
		Cards cards=(Cards) session.getAttribute("cards");
		Map<Integer, Drugandprescripton> map=doctorBusService.findMap(bs.getBy2());
		
		double sum = 0;
		Iterator<Drugandprescripton> it = map.values().iterator();
		// 累加购物项集合每个购物项的小计价格
		while (it.hasNext()) {
			sum += it.next().getSum();		
		}	
		modelMap.put("sum", sum);
		modelMap.put("map", map);
		modelMap.put("pname", cards.getPname());

		return "doctorBus/prescription";
		
	}
	
		/*	添加病历信息*/
	

	@AuthPassport
	@RequestMapping("addHi")
	@ResponseBody
	public Object addHi(
			@RequestParam("deal")Integer deal,
			@RequestParam("brief")String  brief,
			@RequestParam("cid")Integer cid,
			@RequestParam("rid")Integer rid,
			HttpSession session){
		By2State bs =(By2State) session.getAttribute("bs");	
		Doctors doctors= (Doctors) session.getAttribute("doctors");

		Map<String,String> map=new HashMap<String,String>();
			if(deal==1){		
				//添加病历
			/*	 cid, doid, prid,brief, deal,I rid*/
				if(0<doctorBusService.allHistory(cid, doctors.getDoid(),0,brief, deal,rid)){
					map.put("map", "ok");	
				}
			}else{
				if(0<doctorBusService.allHistory(cid, doctors.getDoid(),bs.getBy2(),brief, deal,rid)){
					map.put("map", "ok");	
				}				
			}		
			return map;			
	}	

	@AuthPassport
	@RequestMapping("removes")
	@ResponseBody
	public String removes(
			@RequestParam("drid")Integer drid,
			@RequestParam("prid")Integer prid,
			ModelMap modelMap){
		String mgs="false";
		if(0<doctorBusService.deletedrug(drid,prid)){		
			mgs="true";
		}
		/*删除药方项*/		
		return mgs;		
	}
	
	@AuthPassport
	@RequestMapping("qunnidaye")
	@ResponseBody
	public String updahi(
			@RequestParam("drid")Integer drid,
			@RequestParam("prid")Integer prid,
			@RequestParam("nun")Integer nun,HttpSession session){
	
		/*修改药方项*/
		
		String mgs="false";
		if(0<doctorBusService.updatedrug(nun,drid,prid)){			
			mgs="true";
		}
			
		return mgs;
		
	}
}
