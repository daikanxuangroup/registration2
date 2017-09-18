package com.dkx.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Bookable;
import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.WeekBean;

@Repository("bookableMapper")
public interface BookableMapper {

	//查科室排班信息
	List<WeekBean> findBK(@Param("bklist") List<String> bklist,@Param("deid")Integer deid );
	
	//查科室有效医生
	@Select("select * from doctors where deid = #{deid} and doexist = 1")
	List<Doctors> findAllDoc(@Param("deid") Integer deid);

	Integer addBK(@Param("bk")Bookable bk);

	//删除排班
	@Delete("delete from bookable where doid = #{doid} and bdate = #{date1}")
	Integer delBK(@Param("doid")Integer doid, @Param("date1")Date date1);
}
