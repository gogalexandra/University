package common.domain;

public class Driver extends BaseEntity<Integer> {
    private String name;
    private Integer experience;
    private Integer carId;
    private Integer teamId;


    public Driver(Integer id, String name,  Integer experience, Integer carId, Integer teamId) {
        this.setId(id);
        this.name = name;
        this.experience = experience;
        this.carId = carId;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", experience=" + experience +
                ", carId=" + carId +
                ", teamId=" + teamId +
                '}';
    }
}
