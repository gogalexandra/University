package com.alex.planit.model;

public class MediatorTask {
    public int taskId;
    public String name;
    public String info;
    public int duration;
    public int isDone;

    public MediatorTask() {
    }

    public MediatorTask(int taskId, String name, String info, int duration, int isDone) {
        this.taskId = taskId;
        this.name = name;
        this.info = info;
        this.duration = duration;
        this.isDone = isDone;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int isDone() {
        return isDone;
    }

    public void setDone(int done) {
        isDone = done;
    }
}