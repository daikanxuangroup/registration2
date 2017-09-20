package com.liujiang.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Departs;
import com.daibingjie.pojo.Doctors;
import com.liujiang.pojo.DepartsDoctors;

@Repository("dedoMapper")
public interface DedoMapper {
	
	@Select("select * from departsdoctors where 1=1")
	List<DepartsDoctors> findDedo();
	
	/*@Select("select * from departsdoctors where doid=#{doid}")
	DepartsDoctors findById(Integer doid);*/
	
	@Select("select doname,deid,title,info,monam,monpm,tueam,wedam,wedpm,"
			+ "tuepm,thuam,thupm,friam,fripm,satam,satpm"
			+ ",sunap,sumpm,"
			+ "pcreg,xcreg,doexist,bcost from doctors where doid=#{doid}")
	Doctors find(Integer doid);
	
	/*@Update("update departsdoctors set deid=#{deid,jdbcType=INTEGER},dename=#{dename,jdbcType=VARCHAR},doname=#{doname,jdbcType=VARCHAR},"
			+ "title=#{title,jdbcType=INTEGER},info=#{info,jdbcType=INTEGER},monam=#{monam,jdbcType=INTEGER},monpm=#{monpm,jdbcType=INTEGER},tueam=#{tueam,jdbcType=INTEGER},"
			+ "tuepm=#{tuepm,jdbcType=INTEGER},wedam=#{wedam,jdbcType=INTEGER},"
			+ "wedpm=#{wedpm,jdbcType=INTEGER},thuam=#{thuam,jdbcType=INTEGER},thupm=#{thupm,jdbcType=INTEGER},friam=#{friam,jdbcType=INTEGER},fripm=#{fripm,jdbcType=INTEGER},satam=#{satam,jdbcType=INTEGER},satpm=#{satpm,jdbcType=INTEGER},sunap=#{sunap,jdbcType=INTEGER},sumpm=#{sumpm,jdbcType=INTEGER},"
			+ "pcreg=#{pcreg,jdbcType=INTEGER},"
			+ "xcreg=#{xcreg,jdbcType=INTEGER},doexist=#{doexist,jdbcType=INTEGER},bcost=#{bcost,jdbcType=DOUBLE} where doid=#{doid}")
	int modify(DepartsDoctors doctors);*/
	@Update("update doctors set deid=#{deid,jdbcType=INTEGER},doname=#{doname},"
			+ "title=#{title},info=#{info,jdbcType=INTEGER},monam=#{monam,jdbcType=INTEGER},monpm=#{monpm,jdbcType=INTEGER},tueam=#{tueam,jdbcType=INTEGER},"
			+ "tuepm=#{tuepm,jdbcType=INTEGER},wedam=#{wedam,jdbcType=INTEGER},"
			+ "wedpm=#{wedpm,jdbcType=INTEGER},thuam=#{thuam,jdbcType=INTEGER},thupm=#{thupm,jdbcType=INTEGER},friam=#{friam,jdbcType=INTEGER},fripm=#{fripm,jdbcType=INTEGER},"
			+ "satam=#{satam,jdbcType=INTEGER},satpm=#{satpm,jdbcType=INTEGER},sunap=#{sunap,jdbcType=INTEGER},sumpm=#{sumpm,jdbcType=INTEGER},"
			+ "pcreg=#{pcreg,jdbcType=INTEGER},"
			+ "xcreg=#{xcreg,jdbcType=INTEGER},doexist=#{doexist},bcost=#{bcost} where doid=#{doid}")
	int modify(Doctors doctors);
	
	@Select("select deid,dename,deexist from departs where deexist=1 ")
	List<Departs> findByDeid();

}
