package com.dkx.service;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dkx.dao.TaskMapper;


@Service("TaskService")
@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
public class TaskService {

	@Resource(name="TaskMapper")
	private TaskMapper mapper;
	
	/**
	 * 定时每天0点 将前一天未预约的网上号加到现场
	 * @return
	 */
	@Transactional(isolation = Isolation.DEFAULT,propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	 @Scheduled(cron = "0 0 0 * * ?") 
	public void updateBkNum(){
		System.out.println("定时任务执行");
		mapper.updateBkNum();
	}
}
