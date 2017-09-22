package com.dzl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.dzl.pojo.Cards;
@Repository("cardsMapper")
public interface CardsMapper {
	
	@Insert("insert into cards(cid,pname,sex,phone,idcard,pwd,newdate,remaining,doexist)"
			+ "values(#{cid},#{pname},#{sex},#{phone},#{idcard},#{pwd},default,#{remaining},#{doexist})")
	@SelectKey(keyProperty = "cid", statement = "select seq_cards.nextval from dual",
    resultType = int.class, before = true)
	int addCards(Cards cards);
	
	@Update("update cards set remaining=#{remaining} where cid=#{cid}")
	int updateRamaining(@Param("remaining") Double remaining,@Param("cid") Integer cid);
	
	@Update("update cards set doexist=0 where idcard=#{idcard}")
	int stop(@Param("idcard") String idcard);
	
	@Update("update cards set doexist=1 where idcard=#{idcard}")
	int begin(@Param("idcard") String idcard);
	
	@Select("select cid,pname,sex,phone,idcard,pwd,newdate,remaining,doexist from cards where cid=#{cid} ")
	Cards findByIdcard(@Param("cid") Integer cid);
	
	@Select("select cid,pname,sex,phone,idcard,pwd,newdate,remaining,doexist from cards")
	List<Cards> findAll();
	
	List<Cards> findByPage(@Param("pageNo") Integer pageNo,@Param("pageSize") Integer pageSize,
			@Param("sort") String sort,
			@Param("order") String order,
			@Param("pname") String pname);
	
	int getTotal(@Param("pname") String pname);
}
