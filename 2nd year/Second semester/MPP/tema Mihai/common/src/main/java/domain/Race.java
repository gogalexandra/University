package domain;

import java.util.Date;
import java.util.Objects;

public class Race extends BaseEntity<Integer> {
    private String location;
    private Date date;

    public Race(Integer id, String location, Date date) {
        this.setId(id);
        this.location = location;
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Race{" +
                "location='" + location + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return Objects.equals(getId(), race.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
