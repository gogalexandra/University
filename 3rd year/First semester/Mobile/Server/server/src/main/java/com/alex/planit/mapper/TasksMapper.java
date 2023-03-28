package com.alex.planit.mapper;

import com.alex.planit.dto.*;
import com.alex.planit.model.MediatorTask;
import com.alex.planit.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TasksMapper {

    public Task fromAddReqtoTask(AddTaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.name);
        task.setInfo(taskRequest.info);
        task.setDuration(taskRequest.duration);
        task.setDone(taskRequest.isDone);
        return task;
    }

    public AddTaskResponse fromTasktoAddResp(Task task) {
        AddTaskResponse addTaskResponse = new AddTaskResponse();
        addTaskResponse.taskId = task.getId();
        addTaskResponse.name = task.getName();
        addTaskResponse.info = task.getInfo();
        addTaskResponse.duration = task.getDuration();
        addTaskResponse.isDone = task.getDone();
        return addTaskResponse;
    }

    public Task fromUpdateReqtoTask(UpdateTaskRequest taskRequest) {
        Task task = new Task();
        task.setName(taskRequest.name);
        task.setInfo(taskRequest.info);
        task.setDuration(taskRequest.duration);
        task.setDone(taskRequest.isDone);
        return task;
    }

    public UpdateTaskResponse fromTasktoUpdateResp(Task task) {
        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse();
        updateTaskResponse.taskId = task.getId();
        updateTaskResponse.name = task.getName();
        updateTaskResponse.info = task.getInfo();
        updateTaskResponse.duration = task.getDuration();
        updateTaskResponse.isDone = task.getDone();
        return updateTaskResponse;
    }

    public TaskDto fromTasktoTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.id = task.getId();
        taskDto.name = task.getName();
        taskDto.info = task.getInfo();
        taskDto.duration = task.getDuration();
        taskDto.isDone = task.getDone();
        return taskDto;
    }
}
