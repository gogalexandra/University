package common.domain;

public class Participation extends BaseEntity<Integer> {
    private int team;
    private int race;

    public Participation(Integer id, int teamId, int raceId) {
        setId(id);
        this.team = teamId;
        this.race = raceId;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    @Override
    public String toString() {
        return "Participation{" +
            "team=" + team +
            ", race=" + race +
            '}';
    }
}
