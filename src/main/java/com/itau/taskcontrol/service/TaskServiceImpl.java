package com.itau.taskcontrol.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.itau.taskcontrol.exception.BusinessRuleException;
import com.itau.taskcontrol.exception.RecordNotFoundException;
import com.itau.taskcontrol.model.EnumStatus;
import com.itau.taskcontrol.model.TaskEntity;
import com.itau.taskcontrol.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Override
	public List<TaskEntity> getAllTasks() {
		List<TaskEntity> tasks = new ArrayList<>();
		if (userDetailsService.loggedUserIsAdmin()) {
			tasks = taskRepository.findByOrderByStatusAsc();
		} else {
			tasks = taskRepository.findByUserOrderByStatusAsc(userDetailsService.getLoggedUser());
		}
		return tasks;
	}

	@Override
	public List<TaskEntity> getTaskByStatus(EnumStatus status) {
		List<TaskEntity> tasks = new ArrayList<>();
		if (userDetailsService.loggedUserIsAdmin()) {
			tasks = taskRepository.findByStatus(status);
		} else {
			tasks = taskRepository.findByUserAndStatus(userDetailsService.getLoggedUser(), status);
		}
		return tasks;
	}

	@Override
	public TaskEntity createTask(TaskEntity taskEntity) throws BusinessRuleException {
		taskEntity.setId(null);
		taskEntity.setChangeDate(null);
		if (taskEntity.getStatus() == null) {
			taskEntity.setStatus(EnumStatus.PENDING);
		}
		taskEntity.setCreatedDate(Calendar.getInstance());
		taskEntity.setUser(userDetailsService.getLoggedUser());
		validateFields(taskEntity);
		return taskRepository.save(taskEntity);
	}

	@Override
	public TaskEntity updateTask(TaskEntity taskEntity) throws RecordNotFoundException, BusinessRuleException {
		Optional<TaskEntity> task = taskRepository.findById(taskEntity.getId());
		if (task.isPresent()) {
			if (userDetailsService.loggedUserIsAdmin()
					|| userDetailsService.getLoggedUser().getId().equals(task.get().getUser().getId())) {
				TaskEntity taskUpdated = task.get();
				taskUpdated.setSummary(taskEntity.getSummary());
				validateFields(taskEntity);
				taskUpdated.setDescription(taskEntity.getDescription());
				if (!taskEntity.getStatus().equals(taskUpdated.getStatus())) {
					taskUpdated.setChangeDate(Calendar.getInstance());
				}
				taskUpdated.setStatus(taskEntity.getStatus());
				return taskRepository.save(taskUpdated);
			} else {
				throw new BusinessRuleException(
						"Apenas o usuário criador da tarefa ou administrador pode realizar alterações na tarefa.");
			}
		} else {
			throw new RecordNotFoundException(
					"Nenhuma tarefa encontrada com ID [" + String.valueOf(taskEntity.getId()) + "] para atualização.");
		}
	}

	@Override
	public void deleteTaskById(Long id) throws RecordNotFoundException, BusinessRuleException {
		Optional<TaskEntity> task = taskRepository.findById(id);
		if (task.isPresent()) {
			if (userDetailsService.loggedUserIsAdmin()
					|| userDetailsService.getLoggedUser().getId().equals(task.get().getUser().getId())) {
				taskRepository.deleteById(id);
			} else {
				throw new BusinessRuleException(
						"Apenas o usuário criador da tarefa ou administrador pode excluir a tarefa.");
			}
		} else {
			throw new RecordNotFoundException(
					"Nenhuma tarefa encontrada com ID [" + String.valueOf(id) + "] para remoção.");
		}
	}

	private void validateFields(TaskEntity taskEntity) throws BusinessRuleException {
		if (StringUtils.isEmpty(taskEntity.getSummary())) {
			throw new BusinessRuleException("Campo resumo obrigatório.");
		}
		if (StringUtils.isEmpty(taskEntity.getDescription())) {
			throw new BusinessRuleException("Campo descrição obrigatório.");
		}
		if (taskEntity.getStatus() == null) {
			throw new BusinessRuleException("Campo status obrigatório.");
		}
	}

}
