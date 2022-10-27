package Model;

public class Truck implements Vehicle{
    private int repairCost;

    public Truck(){}

    public Truck(int givenRepairCost){
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
        return "Truck{" +
                "repairCost=" + repairCost +
                '}' + '\n';
    }
}
