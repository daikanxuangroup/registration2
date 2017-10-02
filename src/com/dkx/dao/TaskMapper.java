package com.dkx.dao;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository("TaskMapper")
public interface TaskMapper {
	
	@Update("update bookable set xcum = (xcum+bnum-ynum) where bdate = trunc(sysdate )")
	 int updateBkNum();
}
