package com.itau.taskcontrol.service;

import java.util.List;

import com.itau.taskcontrol.exception.BusinessRuleException;
import com.itau.taskcontrol.exception.RecordNotFoundException;
import com.itau.taskcontrol.model.EnumStatus;
import com.itau.taskcontrol.model.TaskEntity;

public interface TaskService {

	List<TaskEntity> getAllTasks();

	List<TaskEntity> getTaskByStatus(EnumStatus status);

	TaskEntity createTask(TaskEntity taskEntity) throws BusinessRuleException;

	TaskEntity updateTask(TaskEntity taskEntity) throws RecordNotFoundException, BusinessRuleException;

	void deleteTaskById(Long id) throws RecordNotFoundException, BusinessRuleException;

}
