package server.service;

import common.domain.Participation;
import server.repository.DBpersistence.Repository;

import static java.lang.Math.max;

public class ParticipationService {
    private Repository<Integer, Participation> participationRepository;

    public ParticipationService(Repository<Integer, Participation> participationRepository) {
        this.participationRepository = participationRepository;
    }

    public Iterable<Participation> getParticipations() {
        return participationRepository.findAll();
    }

    public void addParticipation(Integer id, Integer teamid, Integer raceid) {
        Participation participation = new Participation(id, teamid, raceid);
        this.participationRepository.save(participation);
    }

    public void deleteParticipation(Integer id) {
        this.participationRepository.delete(id);
    }

    public void updateParticipation(Integer id, Integer teamid, Integer raceid) {
        Participation participation = new Participation(id, teamid, raceid);
        this.participationRepository.update(participation);
    }
}
