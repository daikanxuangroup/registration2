package com.dzl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.daibingjie.pojo.Doctors;
import com.dkx.pojo.Departs;
import com.dzl.pojo.Bookable;
import com.dzl.pojo.Books;
@Repository("booksMapper")
public interface BooksMapper {
	List<Books> findAll(@Param("starttime") Integer starttime,
			@Param("state") Integer state,@Param("idcard") String idcard);
	
	Books findById(@Param("red") Integer red);
	
	int getSnum(@Param("starttime") Integer starttime,@Param("bid") Integer bid);
	
	@Select("select dename from departs where deexist = 1")
	List<String> findAllDename();
	
	@Select("select deid,dename,intro from departs where deexist = 1")
	List<Departs> findUseDe();
	
//	@Select("select doname from doctors where deid=#{deid}") //改成查值班医生 by dkx  2017-9-24 11:01:20
	//用pcreg装现场可预约, xcreg现场已预约
	@Select("select d.doid,d.doname,d.title,b.xcum pcreg,b.xcyum xcreg from bookable b,doctors d where b.doid = d.doid and "
			+ "b.bdate = trunc(sysdate) and b.starttime = (select case when to_char(sysdate,'hh24')>13 "
			+ "then 1 else -1 end from dual) and d.deid = #{deid}") 
	List<Doctors> findAllDoname(@Param("deid") Integer deid);
	
	@Select("select deid from departs where dename=#{dename}")
	int findDeid(@Param("dename") String dename);
	
	@Select("select doid from doctors where doname=#{doname}")
	int findDoid(@Param("doname") String doname);
	
	@Select("select doname from doctors where doid=#{doid}")
	String findDoname(@Param("doid") Integer doid);
	
	@Select("select bcost from doctors where doid=#{doid}")
	double findBcost(@Param("doid") Integer doid);
	
	@Select("select dename from departs de,doctors do where de.deid=do.deid and doid=#{doid}") 
	String findDename(@Param("doid") Integer doid);
	
	@Select("select bid,xcum,xcyum from bookable where doid=#{doid} and bdate=trunc(sysdate) and starttime=#{starttime}")
	Bookable findNyDoid(@Param("doid") Integer doid,
			@Param("starttime") Integer starttime);
	
	//挂完号状态应该为1 ：未看
	@Insert("insert into registration(rid,cid,bid,snum,state) values(#{rid},#{medcard,jdbcType=INTEGER},#{bid},#{snum},1)")
	@SelectKey(keyProperty = "rid", statement = "select seq_registration.nextval from dual", resultType = int.class, before = true)
	int addticket(@Param("medcard") Integer medcard,@Param("bid") Integer bid,@Param("snum") Integer snum);

	@Update("update bookable set xcyum=((select xcyum from bookable where bid=#{bid})+1) where bid=#{bid} ")
	int updatebookable(@Param("bid") Integer bid);
	
	@Select("select cid from cards where idcard=#{idcard}")
	int findMedcard(@Param("idcard") String idcard);
	
	@Select("select pname from cards where cid=#{medcard}")
	String findPname(@Param("medcard") Integer medcard);
	
	@Update("update reservation set state=-1 where red=#{red}")
	int updateReg(@Param("red") Integer red);

	@Select(" select bid from   reservation where red = #{red} ")
	Integer findbid(@Param("red") Integer red);

	@Select("select count(*) from  reservation r,patients p,cards c where r.pid = p.pid and "
			+ "c.idcard = p.idcard and r.red = #{red} and c.cid = #{card}")
	Integer bdCard(@Param("red")Integer red, @Param("card")Integer card);

	//state改为0表示网约已取号
	@Update("update reservation set state = 0 where red = #{red}")
	Integer updateRestate(@Param("red")Integer red);

	
}
