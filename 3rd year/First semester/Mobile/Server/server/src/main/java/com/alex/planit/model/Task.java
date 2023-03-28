package com.alex.planit.model;


import javax.persistence.*;

@Entity
@Table(name = "Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "info")
    private String info;

    @Column(name = "duration")
    private int duration;

    @Column(name = "is_done")
    private int isDone;

    public Task() {
    }

    public Task(int id, String name, String info, int duration, int isDone) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.duration = duration;
        this.isDone = isDone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDone() {
        return isDone;
    }

    public void setDone(int done) {
        isDone = done;
    }
}
