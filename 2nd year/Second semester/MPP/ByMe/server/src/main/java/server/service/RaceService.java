package server.service;

import common.domain.Participation;
import common.domain.Race;
import server.repository.DBpersistence.ParticipationDBRepository;
import server.repository.DBpersistence.Repository;

import java.util.ArrayList;
import java.util.Date;

public class RaceService {
    private Repository<Integer, Race> raceRepository;
    private Repository<Integer, Participation> participationRepository;


    public RaceService(Repository<Integer, Race> raceRepository, Repository<Integer, Participation> participationRepository) {
        this.raceRepository = raceRepository;
        this.participationRepository = participationRepository;
    }
    public Iterable<Race> getRaces() {
        return raceRepository.findAll();
    }

    public void addRace(Integer id, String location, String date) {
        Race race = new Race(id, location, date);
        this.raceRepository.save(race);
    }

    public void deleteRace(Integer id) {
        this.raceRepository.delete(id);
    }

    public void updateRace(Integer id, String location, String date) {
        Race race = new Race(id, location, date);
        this.raceRepository.update(race);
    }

    public Iterable<Participation> getRaceParticipations(Integer id) {
        Iterable<Participation> participations = new ArrayList<>();
        if(participationRepository instanceof ParticipationDBRepository){
            participations = ((ParticipationDBRepository) participationRepository).getParticipationRace(id);
        }
        return participations;
    }

//    public void addProductToReceipt(Long receiptId, Long productId){
//        if(raceRepository instanceof ReceiptDBRepository){
//            int result = ((ReceiptDBRepository) raceRepository).addProductToReceipt(receiptId, productId);
//        }
//    }
//
//    public void deleteProductFromReceipt(Long receiptId, Long productId){
//        if(raceRepository instanceof ReceiptDBRepository){
//            int result = ((ReceiptDBRepository) raceRepository).deleteProductFromReceipt(receiptId, productId);
//        }
//    }

}
