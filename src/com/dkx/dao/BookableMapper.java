package com.dkx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dkx.pojo.WeekBean;

@Repository("bookableMapper")
public interface BookableMapper {

	//查科室排班信息
	List<WeekBean> findBK(@Param("bklist") List<String> bklist,@Param("deid")Integer deid );
	
}
