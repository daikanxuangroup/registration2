package com.dkx.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
}
