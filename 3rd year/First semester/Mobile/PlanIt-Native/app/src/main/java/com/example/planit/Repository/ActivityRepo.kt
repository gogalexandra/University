package com.example.planit.Repository

import com.example.planit.Model.Activity
import com.example.planit.Model.Task
import java.util.*
import kotlin.collections.HashMap

object ActivityRepo{
    var elements = ArrayList<Activity>()
    var elementsName = ArrayList<String>()

    init {
        val date = Date()
        add(Activity("Activity 1", date, "informatii", 17.00, 18.00))
        add(Activity("Activity 2", date, "informatii", 17.00, 18.00))
        add(Activity("Activity 3", date, "informatii", 17.00, 18.00))
        add(Activity("Activity 4", date, "informatii", 17.00, 18.00))

    }
    fun add(activity: Activity){
        elements.add(activity)
        elementsName.add(activity.name)
    }


    fun getOnlyTheNames(): ArrayList<String>{
        for(element in elements){
            elementsName.add(element.name)
        }
        return elementsName
    }

    fun getActivitybyName(item: String): Activity? {
        for (activity in elements){
            if (activity.name == item)
                return activity;
        }
        return null;
    }

    fun update(name: String?, newActivity: Activity?) {
        for (activity in elements){
            if (activity.name == name)
                if (newActivity != null) {
                    if (activity.name !== newActivity.name){
                        elementsName.remove(activity.name)
                        elementsName.add(newActivity.name)
                        activity.name = newActivity.name
                    }
                    activity.date = newActivity.date
                    activity.info = newActivity.info
                    activity.startTime = newActivity.startTime
                    activity.endTime = newActivity.endTime
                }
        }
    }

    fun delete(name: String){
        val activity = getActivitybyName(name)
        elements.remove(activity)
        elementsName.remove(name)
    }
}