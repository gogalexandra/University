package Model;

public class MotorBike implements Vehicle{

    private int repairCost;

    public MotorBike(){
    }

    public MotorBike(int givenRepairCost){
        this.repairCost = givenRepairCost;
    }

    @Override
    public boolean checkRepairCost(int price) {
        return repairCost >= price;
    }

    @Override
    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost){
        this.repairCost = repairCost;
    }


    @Override
    public String toString() {
        return "Motorbike{" +
                "repairCost=" + repairCost +
                '}' + '\n';
    }
}
