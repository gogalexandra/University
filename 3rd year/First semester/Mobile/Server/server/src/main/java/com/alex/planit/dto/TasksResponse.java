package com.alex.planit.dto;

import java.util.ArrayList;
import java.util.List;

public class TasksResponse {
    public List<TaskDto> items = new ArrayList<>();

    public TasksResponse(List<TaskDto> items) {
        this.items.addAll(items);
    }
}
