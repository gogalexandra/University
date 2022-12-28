package Model;

public class Activity {
    private String activityName;
    private int startTime;
    private int endTime;

    //constructors
    public Activity(){
        this.activityName = "";
        this.startTime = 0;
        this.endTime = 0;
    }

    public Activity(String activityName, int startTime, int endTime){
        this.activityName = activityName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //getters

    public String getActivityName() {
        return activityName;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    //setters

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return  activityName + "(" + startTime + " - "+ endTime + ')';
    }
}
