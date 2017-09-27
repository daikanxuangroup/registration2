package com.dzl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.Departs;
import com.dzl.dao.BooksMapper;
import com.dzl.pojo.Bookable;
import com.dzl.pojo.Books;

@Service("booksService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class BooksService {
	
	@Resource(name="booksMapper")
	private BooksMapper booksMapper;
	
	
	
	public List<Books> findAll(Integer starttime,String idcard) {
		List<Books> list=booksMapper.findAll(starttime,1,idcard); //已挂号状态为1
		return list;
	}
	
	public int updateReg(Integer red) {
		int rows=booksMapper.updateReg(red);
		return rows;
	}
	
	public Books findById(Integer red){
		Books books=booksMapper.findById(red);
		return books;
	}
	
	public int getSnum(Integer bid) throws ParseException{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd mm:HH:ss");
		String a= sdf.format(date);
		String c=a+" 00:12:00";
		Date date2=sdf1.parse(c);
		int starttime=-1;
		if((int)date.getTime()>=(int)date2.getTime()){
			starttime=1;
		}
		int snum=booksMapper.getSnum( starttime,bid);
		return snum;
	}
	
	public HashMap<Departs , List<Doctors >> findMessage(){
		HashMap<Departs , List<Doctors >> map=new HashMap<Departs, List<Doctors>>();
		List<Departs> deidlist = booksMapper.findUseDe();  //
//		List<String > listdename=booksMapper.findAllDename(); 
		for (Departs de : deidlist) { 
//			int deid=booksMapper.findDeid(dename); //有问题 如果科室重名会有bug
			List<Doctors> listdoname=booksMapper.findAllDoname(de.getDeid());
			map.put(de, listdoname);
		}
		return map;
	}
	
	public Bookable findBookable(Integer doid) throws ParseException{
//		int doid=booksMapper.findDoid(doname);
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd ");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd mm:HH:ss");
		String a= sdf.format(date);
		String c=a+" 00:13:00";
		Date date2=sdf1.parse(c);
		int starttime=-1;
		if((int)date.getTime()>=(int)date2.getTime()){
			starttime=1;
		}
		Bookable bookable=booksMapper.findNyDoid(doid, starttime);
		double bcost=booksMapper.findBcost(doid);
		String dename=booksMapper.findDename(doid);
		bookable.setDename(dename);
		System.out.println(dename);
		bookable.setBcost(bcost);
		bookable.setDoname(booksMapper.findDoname(doid));
		return bookable;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public int addticket(int medcard,int bid,int snum){
		int rows1=booksMapper.addticket(medcard, bid, snum);
		int rows2=booksMapper.updatebookable(bid);
		if(rows1>0&&rows2>0){
			return 1;
		}else {
			return 0; 
		}
	}
	
	public int findMedcard(String idcard){
		int medcard=booksMapper.findMedcard(idcard);
		return medcard;
	}
	
	public String  findPname(Integer medcard){
		String pname=booksMapper.findPname(medcard);
		return pname;
	}

	public Integer findbid(Integer red) {
		// TODO Auto-generated method stub
		return booksMapper.findbid(red);
	}

	public Integer bdCard(Integer red,Integer card) {
		// TODO Auto-generated method stub
		return booksMapper.bdCard(red,card);
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Integer addticket2(Integer medcard, Integer bid, Integer snum, Integer red) {
		// TODO Auto-generated method stub
		booksMapper.addticket(medcard, bid, snum);
		booksMapper.updatebookable(bid);
		return booksMapper.updateRestate(red);
	}
}
