package Repo;

import Model.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Repo {
    private ArrayList<Activity> allActivities;
    private ArrayList<Activity> doneActivities;

    public Repo(){
        this.allActivities = new ArrayList<Activity>();
        this.doneActivities = new ArrayList<Activity>();
    }

    public Repo(ArrayList<Activity> activities, ArrayList<Activity> doneActivities){
        this.allActivities = activities;
        this.doneActivities = doneActivities;
    }

    public ArrayList<Activity> getAllActivities() {
        return allActivities;
    }

    //should have check st activity name is unique
    public void addActivity(Activity activity){
        this.allActivities.add(activity);
    }

    public void startActivity(String activityName, int startTime) throws Exception {
        boolean found = false;
        for (Activity allActivity : allActivities) {
            if (allActivity.getActivityName().equals(activityName))
                if (allActivity.getStartTime() == 0) {
                    allActivity.setStartTime(startTime);
                    found = true;
                } else
                    throw new Exception("Activity already has a start time!!!");
        }
        if (!found)
            throw new Exception("Activity not registered!!!");
    }

    public void stopActivity(String activityName, int startTime) throws Exception {
        boolean found = false;
        for (Activity allActivity : allActivities) {
            if (allActivity.getActivityName().equals(activityName))
                if (allActivity.getEndTime() == 0) {
                    allActivity.setEndTime(startTime);
                    doneActivities.add(allActivity);
                    found = true;
                } else
                    throw new Exception("Activity already has an end time!!!");
        }
        if (!found)
            throw new Exception("Activity not registered!!!");
    }

    public ArrayList<Activity> findPastActivities(int givenTime, String filterBy, String givenInfo){
        ArrayList<Activity> pastActivities = new ArrayList<>();
        for (Activity allActivity : allActivities) {
            if (allActivity.getEndTime() < givenTime)
                doneActivities.add(allActivity);
        }
        ArrayList<Activity> filteredActivities;
        if (filterBy.equals("type")){
            filteredActivities = (ArrayList<Activity>) doneActivities.stream()
                    .filter(a -> a.getActivityName().equals(givenInfo))
                    .collect(Collectors.toList());
        }
        else{
            filteredActivities = (ArrayList<Activity>) doneActivities.stream()
                    .filter(a -> a.getEndTime() - a.getStartTime() == Integer.valueOf(givenInfo))
                    .collect(Collectors.toList());
        }

        if (filterBy.equals(""))
            return pastActivities;
        else return filteredActivities;
    }

    public int computeNrofPoints(ArrayList<Integer> points){
        int total = 0;
        int index = 0;
        for (Activity activity : doneActivities) {
            total += (activity.getEndTime() - activity.getStartTime()) * points.get(index++);
        }
        return total;
    }

    @Override
    public String toString() {
        return "Activities:" + allActivities.toString();
    }
}
