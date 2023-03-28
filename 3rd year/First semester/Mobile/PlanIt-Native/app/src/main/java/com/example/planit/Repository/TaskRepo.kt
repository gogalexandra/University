package com.example.planit.Repository


import com.example.planit.Model.Task
import kotlin.collections.ArrayList


object TaskRepo{
    var elements = ArrayList<Task>()
    var elementsName = ArrayList<String>()


    init {
        add(Task("Task1","info", 2.00, false ))
        add(Task("Task2","info", 2.00, false ))
        add(Task("Task3","info", 2.00, false ))
        add(Task("Task4","info", 2.00, false ))
        add(Task("Task5","info", 2.00, false ))

    }


    fun add(task: Task){
        elements.add(task)
        elementsName.add(task.name)
    }


    fun getTaskbyName(item: String): Task? {
        for (task in elements){
            if (task.name == item)
                return task;
        }
        return null;
    }

    fun update(name: String?, newTask: Task?) {
        for (task in elements){
            if (task.name == name)
                if (newTask != null) {
                    if (task.name !== newTask.name){
                        elementsName.remove(task.name)
                        elementsName.add(newTask.name)
                        task.name = newTask.name
                    }
                    task.info = newTask.info
                    task.duration = newTask.duration
                    task.isDone = newTask.isDone
                }
        }
    }

    fun delete(name: String){
        val task = getTaskbyName(name)
        elements.remove(task)
        elementsName.remove(name)
    }
}