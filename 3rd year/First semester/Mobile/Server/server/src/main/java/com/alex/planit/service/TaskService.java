package com.alex.planit.service;

import com.alex.planit.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task taskModel);

    List<Task> getTasks();

    void deleteTask(int taskId);

    Task updateTask(int taskId, Task newTask);
}
