package Controller;

import Model.Activity;
import Repo.Repo;

import java.util.ArrayList;

public class Controller {
    private Repo repo;

    public Controller(Repo repo){
        this.repo = repo;
    }

    public Repo getRepo() {
        return repo;
    }

    public void addActivity(String activityName){
        Activity activity = new Activity(activityName, 0, 0);
        this.repo.addActivity(activity);
    }

    public void startActivity(String activityName, int startTime) throws Exception {
        this.repo.startActivity(activityName, startTime);
    }

    public void stopActivity(String activityName, int startTime) throws Exception {
        this.repo.stopActivity(activityName, startTime);
    }

    public ArrayList<Activity> findPastActivities(int givenTime, String filterBy, String givenInfo){
        return this.repo.findPastActivities(givenTime, filterBy, givenInfo);
    }

    public int computeTotalPoints(ArrayList<Integer> points){
        return this.repo.computeNrofPoints(points);
    }
}
