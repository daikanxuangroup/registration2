package com.dzl.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dzl.dao.BooksMapper;
import com.dzl.pojo.Bookable;
import com.dzl.pojo.Books;

@Service("booksService")
@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class BooksService {
	
	@Resource(name="booksMapper")
	private BooksMapper booksMapper;
	
	
	
	public List<Books> findAll(Integer starttime) {
		List<Books> list=booksMapper.findAll(starttime,0);
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
	
	public int getSnum() throws ParseException{
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
		int snum=booksMapper.getSnum( starttime);
		return snum;
	}
	
	public HashMap<String , List<String >> findMessage(){
		HashMap<String , List<String >> map=new HashMap<String, List<String>>();
		List<String > listdename=booksMapper.findAllDename();
		for (String dename : listdename) {
			int deid=booksMapper.findDeid(dename);
			List<String> listdoname=booksMapper.findAllDoname(deid);
			map.put(dename, listdoname);
		}
		return map;
	}
	
	public Bookable findBookable(String doname) throws ParseException{
		int doid=booksMapper.findDoid(doname);
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
		Bookable bookable=booksMapper.findNyDoid(doid, starttime);
		double bcost=booksMapper.findBcost(doname);
		String dename=booksMapper.findDename(doname);
		bookable.setDename(dename);
		System.out.println(dename);
		bookable.setBcost(bcost);
		bookable.setDoname(doname);
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
}
