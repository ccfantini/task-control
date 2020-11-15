package com.itau.taskcontrol.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.itau.taskcontrol.exception.BusinessRuleException;
import com.itau.taskcontrol.exception.RecordNotFoundException;
import com.itau.taskcontrol.model.EnumStatus;
import com.itau.taskcontrol.model.TaskEntity;
import com.itau.taskcontrol.model.UserTaskEntity;
import com.itau.taskcontrol.repository.TaskRepository;
import com.itau.taskcontrol.repository.UserRepository;
import com.itau.taskcontrol.service.TaskService;
import com.itau.taskcontrol.service.TaskServiceImpl;
import com.itau.taskcontrol.service.UserDetailsServiceImpl;

/**
 * Unit tests of services for task management.
 * @author ccfantini
 *
 */
@SpringBootTest
public class TaskServiceMockTest {

	@Mock
	private TaskRepository taskRepository;

	@Mock
	UserDetailsServiceImpl userDetailsService;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private TaskService taskService = new TaskServiceImpl();

	private List<TaskEntity> tasks, tasksCompleted;
	private UserTaskEntity userEntity;

	@BeforeEach
	void setMockOutput() {
		// user
		userEntity = new UserTaskEntity();
		userEntity.setId(1L);
		userEntity.setUsername("user_test");
		userEntity.setPassword("password");
		userEntity.setRole("ADMIN");

		// tasks
		tasks = new ArrayList<>();
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(1L);
		taskEntity.setSummary("resumo 1");
		taskEntity.setDescription("descrição 1");
		taskEntity.setCreatedDate(Calendar.getInstance());
		taskEntity.setStatus(EnumStatus.PENDING);
		taskEntity.setUser(userEntity);
		tasks.add(taskEntity);

		taskEntity = new TaskEntity();
		taskEntity.setId(2L);
		taskEntity.setSummary("resumo 2");
		taskEntity.setDescription("descrição 2");
		taskEntity.setCreatedDate(Calendar.getInstance());
		taskEntity.setStatus(EnumStatus.PENDING);
		taskEntity.setUser(userEntity);
		tasks.add(taskEntity);

		tasksCompleted = new ArrayList<>();
		taskEntity = new TaskEntity();
		taskEntity.setId(3L);
		taskEntity.setSummary("resumo 3");
		taskEntity.setDescription("descrição 3");
		taskEntity.setCreatedDate(Calendar.getInstance());
		taskEntity.setStatus(EnumStatus.COMPLETED);
		taskEntity.setUser(userEntity);
		tasksCompleted.add(taskEntity);

		when(userRepository.findByUsername("user_test")).thenReturn(userEntity);
	}

	@Test
	public void whenGetTasksByStatusPending_thenReturnTasksPending() {
		when(userDetailsService.loggedUserIsAdmin()).thenReturn(true);
		when(taskRepository.findByStatus(EnumStatus.PENDING)).thenReturn(tasks);
		List<TaskEntity> tasks = taskService.getTaskByStatus(EnumStatus.PENDING);
		assertEquals(2, tasks.size());
	}

	@Test
	public void whenGetTasksByStatusCompleted_thenReturnTasksCompleted() {
		when(userDetailsService.loggedUserIsAdmin()).thenReturn(true);
		when(taskRepository.findByStatus(EnumStatus.COMPLETED)).thenReturn(tasksCompleted);
		List<TaskEntity> tasks = taskService.getTaskByStatus(EnumStatus.COMPLETED);
		assertEquals(1, tasks.size());
	}

	@Test
	public void whenGetAllTasks_thenReturnAllTasks() {
		when(userDetailsService.loggedUserIsAdmin()).thenReturn(true);
		List<TaskEntity> allTasks = new ArrayList<>();
		allTasks.addAll(tasks);
		allTasks.addAll(tasksCompleted);
		when(taskRepository.findByOrderByStatusAsc()).thenReturn(allTasks);
		List<TaskEntity> tasks = taskService.getAllTasks();
		assertEquals(3, tasks.size());
	}

	@Test
	public void whenCreateTask_thenReturnTask() {
		when(userDetailsService.getLoggedUser()).thenReturn(userEntity);
		TaskEntity task = tasks.get(0);
		when(taskRepository.save(task)).thenReturn(task);
		try {
			TaskEntity result = taskService.createTask(task);
			assertEquals(task, result);
		} catch (BusinessRuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenCreateTask_thenValidateTask() {
		when(userDetailsService.getLoggedUser()).thenReturn(userEntity);
		TaskEntity task = tasks.get(0);
		task.setSummary(null);
		when(taskRepository.save(task)).thenReturn(task);
		try {
			taskService.createTask(task);
		} catch (BusinessRuleException e) {
			assertEquals("Campo resumo obrigatório.", e.getMessage());
		}
	}

	@Test
	public void whenUpdateTask_thenReturnTask() {
		when(userDetailsService.loggedUserIsAdmin()).thenReturn(true);
		when(userDetailsService.getLoggedUser()).thenReturn(userEntity);
		TaskEntity task = tasks.get(0);
		Optional<TaskEntity> taskOptional = Optional.of(task);
		when(taskRepository.findById(task.getId())).thenReturn(taskOptional);
		task.setSummary("task updated");
		task.setStatus(EnumStatus.COMPLETED);
		when(taskRepository.save(task)).thenReturn(task);
		try {
			TaskEntity result = taskService.updateTask(task);
			assertEquals("task updated", result.getSummary());
		} catch (BusinessRuleException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void whenDeleteTask_thenSuccess() {
		userEntity.setId(10L);
		when(userDetailsService.loggedUserIsAdmin()).thenReturn(false);
		when(userDetailsService.getLoggedUser()).thenReturn(userEntity);
		TaskEntity task = tasks.get(0);
		Optional<TaskEntity> taskOptional = Optional.of(task);
		when(taskRepository.findById(task.getId())).thenReturn(taskOptional);
		task.setUser(userEntity);
		try {
			taskService.deleteTaskById(task.getId());
		} catch (BusinessRuleException e) {
			e.printStackTrace();
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
	}

}
