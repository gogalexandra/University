package com.alex.planit.controller;

import com.alex.planit.dto.*;
import com.alex.planit.mapper.TasksMapper;
import com.alex.planit.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alex.planit.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
public class TasksController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TasksMapper tasksMapper;


    @PostMapping
    public ResponseEntity<AddTaskResponse> addTask(@RequestBody AddTaskRequest addTaskRequest) {
        Task taskModel = this.tasksMapper.fromAddReqtoTask(addTaskRequest);
        AddTaskResponse addTaskResponse = this.tasksMapper.fromTasktoAddResp(this.taskService.createTask(taskModel));
        return ResponseEntity.ok(addTaskResponse);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<TasksResponse> getAllTasks() {
        List<Task> taskList = this.taskService.getTasks();
        TasksResponse seatsForAdminResponse = new TasksResponse(taskList.stream()
                .map(s -> tasksMapper.fromTasktoTaskDto(s))
                .collect(Collectors.toList()));
        return ResponseEntity.ok(seatsForAdminResponse);
    }

    @DeleteMapping(path = "/all/{id}")
    public void deleteTask(@PathVariable("id") int id){
        this.taskService.deleteTask(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UpdateTaskResponse> updateTask(@PathVariable("id") int id, @RequestBody UpdateTaskRequest updateTaskRequest){
        Task taskModel = this.tasksMapper.fromUpdateReqtoTask(updateTaskRequest);
        UpdateTaskResponse updateTaskResponse = this.tasksMapper.fromTasktoUpdateResp(this.taskService.updateTask(id, taskModel));
        return ResponseEntity.ok(updateTaskResponse);
    }
}