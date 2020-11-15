package com.itau.taskcontrol.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itau.taskcontrol.exception.BusinessRuleException;
import com.itau.taskcontrol.exception.RecordNotFoundException;
import com.itau.taskcontrol.model.EnumStatus;
import com.itau.taskcontrol.model.TaskEntity;
import com.itau.taskcontrol.service.TaskService;
import com.itau.taskcontrol.to.Message;

/**
 * Class responsible for managing tasks using the REST protocol.
 * @author ccfantini
 *
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	TaskService service;

	/**
	 * Gets all tasks of the logged in user.
	 * If the user has an administrator profile, then it returns all tasks, including those of other users.
	 * @return Task List
	 */
	@GetMapping
	public ResponseEntity<List<TaskEntity>> getAll() {
		List<TaskEntity> tasks = service.getAllTasks();
		return new ResponseEntity<List<TaskEntity>>(tasks, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Gets the tasks of the user logged in by status.
	 * If the user has an administrator profile, then it returns all tasks by status, including those of other users.
	 * @param status EnumStatus attribute can be 0 - PENDING or 1 - COMPLETED
	 * @return Task List
	 */
	@GetMapping("/status/{status}")
	public ResponseEntity<List<TaskEntity>> getByStatus(@PathVariable EnumStatus status) {
		List<TaskEntity> tasks = service.getTaskByStatus(status);
		return new ResponseEntity<List<TaskEntity>>(tasks, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Register a new task.
	 * @param taskEntity Entity to be registered
	 * @return Registered TaskEntity
	 * @throws BusinessRuleException Returns business rules error
	 */
	@PostMapping
	public ResponseEntity<TaskEntity> create(@RequestBody(required = true) TaskEntity taskEntity)
			throws BusinessRuleException {
		TaskEntity task = service.createTask(taskEntity);
		return new ResponseEntity<TaskEntity>(task, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Update task.
	 * @param taskEntity Entity to be updated
	 * @return Registered TaskEntity
	 * @throws RecordNotFoundException Returns error when TaskEntity not found
	 * @throws BusinessRuleException Returns business rules error
	 */
	@PutMapping
	public ResponseEntity<TaskEntity> update(@RequestBody(required = true) TaskEntity taskEntity)
			throws RecordNotFoundException, BusinessRuleException {
		TaskEntity task = service.updateTask(taskEntity);
		return new ResponseEntity<TaskEntity>(task, new HttpHeaders(), HttpStatus.OK);
	}

	/**
	 * Deletes a task by ID
	 * @param id Identification of the task to be deleted
	 * @return Message of success when deleted
	 * @throws RecordNotFoundException Returns error when TaskEntity not found
	 * @throws BusinessRuleException Returns business rules error
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Message> deleteById(@PathVariable Long id) throws RecordNotFoundException, BusinessRuleException {
		service.deleteTaskById(id);
		return new ResponseEntity<Message>(new Message("Tarefa removida com sucesso."), new HttpHeaders(),
				HttpStatus.OK);
	}
	
	/*@GetMapping("/generatepassword")
	public ResponseEntity<Message> generatepassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("juli2020"));
		passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("caio2020"));
		passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("isab2020"));
		passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("gabi2020"));
		return new ResponseEntity<Message>(new Message("sucesso."), new HttpHeaders(),
				HttpStatus.OK);		
	}*/

}
