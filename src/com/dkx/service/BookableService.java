package com.dkx.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.daibingjie.pojo.Bookable;
import com.daibingjie.pojo.Doctors;
import com.dkx.dao.BookableMapper;
import com.dkx.pojo.WeekBean;

@Service("bookableService")
@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
public class BookableService {

	@Resource(name="bookableMapper")
	private BookableMapper mapper;
	
	public List<WeekBean> findBookable(List<String> list, Integer deid) {
		// TODO Auto-generated method stub
		return mapper.findBK(list, deid);
	}
	
	public List<Doctors> findDoctors(Integer deid){
		return mapper.findAllDoc(deid);
	}

	//新增排班
	@Transactional(isolation = Isolation.DEFAULT,propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Integer addBookable(Bookable bk) {
		// TODO Auto-generated method stub
		return mapper.addBK( bk);
	}

	//删除排班
	@Transactional(isolation = Isolation.DEFAULT,propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public Integer deleteBookable(Integer doid, Date date1) {
		// TODO Auto-generated method stub
		return mapper.delBK( doid,  date1);
	}

	
}
