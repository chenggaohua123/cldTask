package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.model.TaskInfo;

public interface TaskManageService {
	/**
	 * 查询所有的定时任务配置
	 * @return
	 */
	public List<TaskInfo> queryAllTaskList();
	
	/**
	 * 更新定时任务的执行时间
	 * @param taskInfo
	 * @return
	 */
	public int updateTaskExcuTimeByNameAndGroupName(TaskInfo taskInfo);
	
	/**
	 * 根据任务的名字和组查询定时任务信息
	 * @param taskName
	 * @param groupName
	 * @return
	 */
	public TaskInfo queryTaskInfoByTaskNameAndGroupName(String taskName,String groupName);
	
	
	/**
	 * 保存定时任务执行历史记录
	 * @param taskInfo
	 * @return
	 */
	public int saveTaskExcuLog(TaskInfo taskInfo);
	
}
