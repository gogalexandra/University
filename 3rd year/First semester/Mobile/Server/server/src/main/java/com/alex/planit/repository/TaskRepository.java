package com.alex.planit.repository;

import com.alex.planit.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository  extends JpaRepository<Task, Integer> {
}
