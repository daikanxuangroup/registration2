package com.dzl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dzl.pojo.Bookable;
import com.dzl.pojo.Books;
@Repository("booksMapper")
public interface BooksMapper {
	List<Books> findAll(@Param("starttime") Integer starttime,
			@Param("state") Integer state);
	
	Books findById(@Param("red") Integer red);
	
	int getSnum(@Param("starttime") Integer starttime);
	
	@Select("select dename from departs")
	List<String> findAllDename();
	
	@Select("select doname from doctors where deid=#{deid}")
	List<String> findAllDoname(@Param("deid") Integer deid);
	
	@Select("select deid from departs where dename=#{dename}")
	int findDeid(@Param("dename") String dename);
	
	@Select("select doid from doctors where doname=#{doname}")
	int findDoid(@Param("doname") String doname);
	
	@Select("select bcost from doctors where doname=#{doname}")
	double findBcost(@Param("doname") String doname);
	
	@Select("select dename from departs de,doctors do where de.deid=do.deid and doname=#{doname}") 
	String findDename(@Param("doname") String doname);
	
	@Select("select bid,xcum,xcyum from bookable where doid=#{doid} and bdate=trunc(sysdate) and starttime=#{starttime}")
	Bookable findNyDoid(@Param("doid") Integer doid,
			@Param("starttime") Integer starttime);
	
	@Insert("insert into registration(rid,cid,bid,snum,state) values(#{rid},#{medcard,jdbcType=INTEGER},#{bid},#{snum},2)")
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
}
