package com.itau.taskcontrol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itau.taskcontrol.model.EnumStatus;
import com.itau.taskcontrol.model.TaskEntity;
import com.itau.taskcontrol.model.UserTaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

	List<TaskEntity> findByOrderByStatusAsc();
	
	List<TaskEntity> findByStatus(EnumStatus status);
	
	List<TaskEntity> findByUserOrderByStatusAsc(UserTaskEntity user);
	
	List<TaskEntity> findByUserAndStatus(UserTaskEntity user, EnumStatus status);
	
}
