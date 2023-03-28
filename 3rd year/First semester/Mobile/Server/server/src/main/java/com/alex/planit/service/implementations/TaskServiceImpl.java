package com.alex.planit.service.implementations;

import com.alex.planit.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alex.planit.repository.TaskRepository;
import com.alex.planit.service.TaskService;

import java.util.List;
import java.util.Optional;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(Task taskModel) {
        return taskRepository.save(taskModel);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(int taskId){
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task updateTask(int taskId, Task newTask){
        Optional<Task> taskToUpdate = this.taskRepository.findById(taskId);
        if (taskToUpdate.isPresent()){
            taskToUpdate.get().setName(newTask.getName());
            taskToUpdate.get().setInfo(newTask.getInfo());
            taskToUpdate.get().setDuration(newTask.getDuration());
            taskToUpdate.get().setDone(newTask.getDone());
            return taskRepository.save(taskToUpdate.get());
        }
        return new Task();
    }
}
