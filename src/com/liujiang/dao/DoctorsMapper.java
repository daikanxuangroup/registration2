package com.liujiang.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Admins;
import com.liujiang.pojo.Departs;
import com.liujiang.pojo.Doctors;

@Repository("doctorsMapper")
public interface DoctorsMapper {
	
	List<Doctors> findAll();
	
	@Select("select * from doctors where doid=#{doid}")
	Doctors findById(Integer doid);
	
	@Update("update doctors set deid=#{deid,jdbcType=INTEGER},title=#{title},doname=#{doname},info=#{info,jdbcType=VARCHAR},bcost=#{bcost},doexist=#{doexist},pcreg=#{pcreg,jdbcType=INTEGER},xcreg=#{xcreg,jdbcType=INTEGER},"
			+ "monam=#{monam,jdbcType=INTEGER},monpm=#{monpm,jdbcType=INTEGER},tueam=#{tueam,jdbcType=INTEGER},tuepm=#{tuepm,jdbcType=INTEGER},wedam=#{wedam,jdbcType=INTEGER},wedpm=#{wedpm,jdbcType=INTEGER},thuam=#{thuam,jdbcType=INTEGER},thupm=#{thupm,jdbcType=INTEGER},"
			+ "friam=#{friam,jdbcType=INTEGER},fripm=#{fripm,jdbcType=INTEGER},satam=#{satam,jdbcType=INTEGER},satpm=#{satpm,jdbcType=INTEGER},sunap=#{sunap,jdbcType=INTEGER},sumpm=#{sumpm,jdbcType=INTEGER}  where doid=#{doid}")
	int modify(Doctors doctors);
	
	
	@Update("update doctors set doexist=#{doexist} where doid=#{doid}")
	int modifyState(@Param("doid")Integer doid,@Param("doexist")Integer doexist);
	
	@Insert("insert into doctors(doid,deid,title,doname,info,bcost,doexist,pcreg,"
			+ "xcreg,monam,monpm,tueam,tuepm,wedam,wedpm,thuam,thupm,friam,fripm,satam,satpm,sunap,sumpm) "
			+ "values(#{doid},#{deid},#{title},#{doname},#{info,jdbcType=VARCHAR},#{bcost},1,"
			+ "#{pcreg,jdbcType=INTEGER},"
			+ "#{xcreg,jdbcType=INTEGER},"
			+ "#{monam,jdbcType=INTEGER},"
			+ "#{monpm,jdbcType=INTEGER},"
			+ "#{tueam,jdbcType=INTEGER},"
			+ "#{tuepm,jdbcType=INTEGER},"
			+ "#{wedam,jdbcType=INTEGER},"
			+ "#{wedpm,jdbcType=INTEGER},"
			+ "#{thuam,jdbcType=INTEGER},"
			+ "#{thupm,jdbcType=INTEGER},"
			+ "#{friam,jdbcType=INTEGER},"
			+ "#{fripm,jdbcType=INTEGER},"
			+ "#{satam,jdbcType=INTEGER},"
			+ "#{satpm,jdbcType=INTEGER},"
			+ "#{sunap,jdbcType=INTEGER},"
			+ "#{sumpm,jdbcType=INTEGER})")
	@SelectKey(keyProperty="doid",statement="select seq_doctors.nextval from dual",resultType=int.class,before=true)
	int add(Doctors doctors);
	
	@Select("select seq_doctors.currval from dual")
	int findDoc();
	
	@Select("select aname from admins where doid=#{doid}")
	Admins findDoid(Integer doid);
	
	/*@Insert("insert into doctors(doid,deid,title,doname,bcost,doexist) values(#{doid},#{deid},#{title},#{doname},#{bcost},#{doexist})")
	@SelectKey(keyProperty="doid",statement="select seq_doctors.nextval from dual",resultType=int.class,before=true)
	int add(Doctors doctors);*/
	
	@Select("select * from departs where deexist=1 ")
	List<Departs> findDep();
	
	@Insert("insert into admins(aid,aname,pwd,state,aexist,doid) values(#{aid},#{aname},'111111',2,1,#{doid})")
	@SelectKey(keyProperty="aid",statement={"select seq_admins.nextval from dual"},resultType=int.class,before=true)
	int addAdmins(@Param("doid")int doid,@Param("aname")String aname);
	
	@Select("select count(red) from reservation r left join bookable  b on r.bid=b.bid "
			+ "where bdate >  trunc(sysdate) and b.doid=#{doid} and state <> 0 ")
	int select1(Integer doid);
	
	@Select("select count(rid) from registration r left join bookable  b on r.bid=b.bid "
			+ "where bdate >=  trunc(sysdate) and b.doid=#{doid} and state <>0 " )
	int select2(Integer doid);
	
	@Update("update doctors set doexist=#{doexist} where doid=#{doid}")
	int updateState(@Param("doid")Integer doid,@Param("doexist") Integer doexist);

	@Select("select aid from admins where doid = #{doid}")
	Integer findDocAid(@Param("doid")Integer doid);

	@Update("update admins set aexist=#{doexist} where aid =#{aid}")
	int updateAdState(@Param("aid")Integer aid, @Param("doexist") Integer doexist);

	@Select("select count(*) from admins where aname = #{aname} ")
	int findUsername(@Param("aname")String aname);

	@Select("select deexist from departs e,doctors o where e.deid= o.deid and doid=#{doid}")
	int ckDeState(@Param("doid")Integer doid);

	@Select("select state from admins where doid = #{doid}")
	Integer ckState(Integer doid);
	
}
