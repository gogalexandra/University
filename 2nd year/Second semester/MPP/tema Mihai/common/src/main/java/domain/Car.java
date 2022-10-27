package domain;

public class Car extends BaseEntity<Integer> {
    private String name;
    private Integer horsepower;
    private Integer maxSpeed;

    public Car(Integer id, String name, Integer horsepower, Integer maxSpeed) {
        this.setId(id);
        this.name = name;
        this.horsepower = horsepower;
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "ID: " + getId()  + " Car: " + "name= " + name +
                " horsepower= " + horsepower +
                " maxSpeed= " + maxSpeed;
    }
}
