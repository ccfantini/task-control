package com.itau.taskcontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itau.taskcontrol.model.UserTaskEntity;

@Repository
public interface UserRepository extends JpaRepository<UserTaskEntity, Long> {

    UserTaskEntity findByUsername(String username);
}
