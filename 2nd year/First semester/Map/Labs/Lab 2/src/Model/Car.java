package Model;

public class Car implements Vehicle{
    private int repairCost;

    public Car(){
    }

    public Car(int givenRepairCost){
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
        return "Car{" +
                "repairCost=" + repairCost +
                '}'+ '\n';
    }
}
