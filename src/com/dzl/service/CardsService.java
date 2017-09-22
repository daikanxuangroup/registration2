package com.dzl.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dzl.dao.CardsMapper;
import com.dzl.pojo.Cards;


@Service("cardsService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class CardsService {
	 @Resource(name = "cardsMapper")
	 private CardsMapper cardsMapper;
	 
	 /**
	  * 注册医疗卡
	  */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	 public int addCards(Cards cards) {
		int rows=cardsMapper.addCards(cards);
		return rows;
	}
	 
	 /**
	  * 充值
	  */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	 public int updateRemaining(Double money,Integer cid) {
		Cards cards=cardsMapper.findByIdcard(cid);
		cards.setRemaining(cards.getRemaining()+money);
		int rows=cardsMapper.updateRamaining(cards.getRemaining(), cid);
		return rows;
	}
	
	/**
	  * 收费
	  */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	 public int updateRemaining2(Double bcost,Integer cid) {
		Cards cards=cardsMapper.findByIdcard(cid);
		cards.setRemaining(cards.getRemaining()-bcost);
		int rows=cardsMapper.updateRamaining(cards.getRemaining(), cid);
		return rows;
	}
	 
	 /**
	  * 余额查询
	  */
	 public Cards findByIdcard(Integer cid) {
			Cards cards=cardsMapper.findByIdcard(cid);
			return cards;
		}
	 
	 /**
	  * 分页查询医疗卡信息
	  * @param pageNo
	  * @param pageSize
	  * @param sort
	  * @param order
	  * @param title
	  * @return
	  */
	 public List<Cards> findByPage(int pageNo,int pageSize,String sort,String order,String pname){
			List<Cards> list=cardsMapper.findByPage(pageNo, pageSize,sort,order, pname);
			return list;
		}
		
		
		/**
		 * 分页查询总记录条数
		 * @param title
		 * @return
		 */
		public int getTotal(String pname) {
			int rows=cardsMapper.getTotal(pname);
			return rows;
		}
		
		/**
		 * 医疗卡停用
		 */
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public int stop(String idcard) {
			int rows=cardsMapper.stop(idcard);
			return rows;
		}
		
		/**
		 * 医疗卡启用
		 */
		@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
		public int begin(String idcard) {
			int rows=cardsMapper.begin(idcard);
			return rows;
		}
}
