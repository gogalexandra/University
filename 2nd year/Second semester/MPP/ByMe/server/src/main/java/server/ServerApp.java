package server;

import common.EntityConverter;
import common.F1Service;
import main.java.common.Message;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServerApp {
    public static void main(String[] args){
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext("server.config");

        TcpServer tcpServer =context.getBean(TcpServer.class);
        F1Service f1Service = context.getBean(F1Service.class);

        tcpServer.addHandler(F1Service.GET_DRIVERS, request -> {
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getDrivers().get().spliterator(), false)
                                .map(EntityConverter::stringifyDriver)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.ADD_DRIVER, request -> {
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.addDriver(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])).get();
                return new Message(Message.OK, message);

            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.DELETE_DRIVER, request -> {
            try{
                String message = f1Service.deleteDriver(Integer.parseInt(request.getBody())).get();
                return new Message(Message.OK, message);
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.UPDATE_DRIVER, request -> {
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.updateDriver(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])).get();
                return new Message(Message.OK, message);

            } catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_CAR, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getCars().get().spliterator(), false)
                                .map(EntityConverter::stringifyCar)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.ADD_CAR, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.addCar(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.DELETE_CAR, request ->{
            try{
                String message = f1Service.deleteCar(Integer.parseInt(request.getBody())).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.UPDATE_CAR, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.updateCar(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_CAR_DRIVER, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getCarDriver(Integer.parseInt(request.getBody())).get().spliterator(), false)
                                .map(EntityConverter::stringifyDriver)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_PARTICIPATION, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getParticipations().get().spliterator(), false)
                                .map(EntityConverter::stringifyParticipation)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.ADD_PARTICIPATION, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.addParticipation(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.DELETE_PARTICIPATION, request ->{
            try{
                String message = f1Service.deleteParticipation(Integer.parseInt(request.getBody())).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.UPDATE_PARTICIPATION, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.updateParticipation(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2])).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });


        tcpServer.addHandler(F1Service.GET_RACE, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getRaces().get().spliterator(), false)
                                .map(EntityConverter::stringifyRace)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.ADD_RACE, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.addRace(Integer.parseInt(tokens[0]), tokens[1], tokens[2]).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.DELETE_RACE, request ->{
            try{
                String message = f1Service.deleteRace(Integer.parseInt(request.getBody())).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.UPDATE_RACE, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.updateRace(Integer.parseInt(tokens[0]), tokens[1], tokens[2]).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_RACE_PARTICIPATION, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getRaceParticipation(Integer.parseInt(request.getBody())).get().spliterator(), false)
                                .map(EntityConverter::stringifyParticipation)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_TEAM, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getTeams().get().spliterator(), false)
                                .map(EntityConverter::stringifyTeam)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.ADD_TEAM, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.addTeam(Integer.parseInt(tokens[0]), tokens[1],Integer.parseInt(tokens[2])).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.DELETE_TEAM, request ->{
            try{
                String message = f1Service.deleteTeam(Integer.parseInt(request.getBody())).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.UPDATE_TEAM, request ->{
            try{
                String[] tokens = request.getBody().split(",");
                String message = f1Service.updateTeam(Integer.parseInt(tokens[0]), tokens[1],Integer.parseInt(tokens[2])).get();
                return new Message(Message.OK, message);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_TEAM_PARTICIPATION, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getTeamParticipation(Integer.parseInt(request.getBody())).get().spliterator(), false)
                                .map(EntityConverter::stringifyParticipation)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.addHandler(F1Service.GET_TEAM_DRIVERS, request ->{
            try{
                return new Message(Message.OK,
                        StreamSupport.stream(f1Service.getTeamDrivers(Integer.parseInt(request.getBody())).get().spliterator(), false)
                                .map(EntityConverter::stringifyDriver)
                                .collect(Collectors.joining("!")));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });
        tcpServer.startServer();
    }
}
