package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.dao.TaskManageDao;
import com.fhtpay.task.model.TaskInfo;

public class TaskManageServiceImpl implements TaskManageService {
	private TaskManageDao taskManageDao;

	public TaskManageDao getTaskManageDao() {
		return taskManageDao;
	}

	public void setTaskManageDao(TaskManageDao taskManageDao) {
		this.taskManageDao = taskManageDao;
	}

	@Override
	public List<TaskInfo> queryAllTaskList() {
		return taskManageDao.queryAllTaskList();
	}

	@Override
	public int updateTaskExcuTimeByNameAndGroupName(TaskInfo taskInfo) {
		taskManageDao.saveTaskExcuLog(taskInfo);
		return taskManageDao.updateTaskExcuTimeByNameAndGroupName(taskInfo);
	}

	@Override
	public TaskInfo queryTaskInfoByTaskNameAndGroupName(String taskName,
			String groupName) {
		return taskManageDao.queryTaskInfoByTaskNameAndGroupName(taskName, groupName);
	}

	@Override
	public int saveTaskExcuLog(TaskInfo taskInfo) {
		return taskManageDao.saveTaskExcuLog(taskInfo);
	}
}
