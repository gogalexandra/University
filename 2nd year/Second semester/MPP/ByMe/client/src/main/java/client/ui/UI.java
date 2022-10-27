package client.ui;

import common.F1Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class UI {
    private final F1Service service;

    public UI(F1Service service) {
        this.service = service;
    }

    public void menu() {
        //driver
        System.out.println("Insert 0 to exit.");
        System.out.println("Insert 1 to add a driver.");
        System.out.println("Insert 2 to delete a driver.");
        System.out.println("Insert 3 to update a driver.");
        System.out.println("Insert 4 to print all drivers.");
        //car
        System.out.println("Insert 5 to add a car.");
        System.out.println("Insert 6 to delete a car.");
        System.out.println("Insert 7 to update a car.");
        System.out.println("Insert 8 to print all cars.");
        System.out.println("Insert 9 to print a car's drivers.");
        //participation
        System.out.println("Insert 10 to add a participation.");
        System.out.println("Insert 11 to delete a participation.");
        System.out.println("Insert 12 to update a participation.");
        System.out.println("Insert 13 to print all participations.");
        //race
        System.out.println("Insert 14 to add a race.");
        System.out.println("Insert 15 to delete a race.");
        System.out.println("Insert 16 to update a race.");
        System.out.println("Insert 17 to print all races.");
        System.out.println("Insert 18 to print all race participations.");
        //team
        System.out.println("Insert 19 to add a team.");
        System.out.println("Insert 20 to delete a team.");
        System.out.println("Insert 21 to update a team.");
        System.out.println("Insert 22 to print all teams.");
        System.out.println("Insert 23 to print all team drivers.");
        System.out.println("Insert 24 to print all team participations.");

        System.out.println("Insert option: ");
    }

    public void run() {
        Scanner reader = new Scanner(System.in);
        int opt;
        while (true) {
            menu();
            opt = reader.nextInt();
            System.out.println(opt);
            switch (opt) {
                case 0:
                    return;
                case 1:
                    addDriverUI();
                    break;
                case 2:
                    deleteDriverUI();
                    break;
                case 3:
                    updateDriverUI();
                    break;
                case 4:
                    printAllDriversUI();
                    break;
                case 5:
                    addCarUI();
                    break;
                case 6:
                    deleteCarUI();
                    break;
                case 7:
                    updateCarUI();
                    break;
                case 8:
                    printAllCarsUI();
                    break;
                case 9:
                    printCarDriverUI();
                    break;
                case 10:
                    addParticipationUI();
                    break;
                case 11:
                    deleteParticipationUI();
                    break;
                case 12:
                    updateParticipationUI();
                    break;
                case 13:
                    printAllParticipationsUI();
                    break;
                case 14:
                    addRaceUI();
                    break;
                case 15:
                    deleteRaceUI();
                    break;
                case 16:
                    updateRaceUI();
                    break;
                case 17:
                    printAllRacesUI();
                    break;
                case 18:
                    printAllRaceParticipationsUI();
                    break;
                case 19:
                    addTeamUI();
                    break;
                case 20:
                    deleteTeamUI();
                    break;
                case 21:
                    updateTeamUI();
                    break;
                case 22:
                    printAllTeamsUI();
                    break;
                case 23:
                    printAllTeamDriversUI();
                    break;
                case 24:
                    printAllTeamParticipationsUI();
                    break;
            }

        }
    }

    private void printAllTeamParticipationsUI() {
        System.out.println("Provide the id of the team participations you want to see: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        service.getTeamParticipation(id).whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void printAllTeamDriversUI() {
        System.out.println("Provide the id of the team drivers you want to see: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        service.getTeamDrivers(id).whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void printAllTeamsUI() {
        service.getTeams().whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void updateTeamUI() {
        System.out.println("Read updated team {id, name, teamprincipalid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer teamprincipalid = Integer.valueOf(bufferRead.readLine());

            service.updateTeam(id, name, teamprincipalid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteTeamUI() {
        System.out.println("Provide the id of the team you want to delete: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        this.service.deleteTeam(id);
    }

    private void addTeamUI() {
        System.out.println("Read team {id, name, teamprincipalid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer teamprincipalid = Integer.valueOf(bufferRead.readLine());

            service.addTeam(id, name, teamprincipalid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void printAllRaceParticipationsUI() {
        System.out.println("Provide the id of the race participationd you want to see: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        service.getRaceParticipation(id).whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void printAllRacesUI() {
        service.getRaces().whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void updateRaceUI() {
        System.out.println("Read updated race {id, teamid, raceid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String location = bufferRead.readLine();
            String date = bufferRead.readLine();

            service.updateRace(id, location, date).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteRaceUI() {
        System.out.println("Provide the id of the race you want to delete: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        this.service.deleteRace(id);
    }

    private void addRaceUI() {
        System.out.println("Read race {id, location, date}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String location = bufferRead.readLine();
            String date = bufferRead.readLine();

            service.addRace(id, location, date).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void printAllParticipationsUI() {
        service.getParticipations().whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void updateParticipationUI() {
        System.out.println("Read updated participation {id, teamid, raceid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            Integer teamid = Integer.valueOf(bufferRead.readLine());
            Integer raceid = Integer.valueOf(bufferRead.readLine());

            service.addParticipation(id, teamid, raceid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteParticipationUI() {
        System.out.println("Provide the id of the participation you want to delete: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        this.service.deleteParticipation(id);
    }

    private void addParticipationUI() {
        System.out.println("Read participation {id, teamid, raceid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            Integer teamid = Integer.valueOf(bufferRead.readLine());
            Integer raceid = Integer.valueOf(bufferRead.readLine());

            service.addParticipation(id, teamid, raceid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void printCarDriverUI() {
        System.out.println("Provide the id of the car: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        service.getCarDriver(id).whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void printAllDriversUI() {
        service.getDrivers().whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void addDriverUI() {
        System.out.println("Read driver {id, name, experience, carid, teamid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer experience = Integer.valueOf(bufferRead.readLine());
            Integer carid = Integer.valueOf(bufferRead.readLine());
            Integer teamid = Integer.valueOf(bufferRead.readLine());

            service.addDriver(id, name, experience, carid, teamid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateDriverUI() {
        System.out.println("Read updated driver {id, name, experience, carid, teamid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer experience = Integer.valueOf(bufferRead.readLine());
            Integer carid = Integer.valueOf(bufferRead.readLine());
            Integer teamid = Integer.valueOf(bufferRead.readLine());
            service.updateDriver(id, name, experience, carid, teamid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteDriverUI() {
        System.out.println("Provide the id of the driver you want to delete: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        this.service.deleteDriver(id);
    }

    private void printAllCarsUI() {
        service.getDrivers().whenComplete((result, exception) -> {
            if (exception == null)
                StreamSupport.stream(result.spliterator(), false).forEach(System.out::println);
            else System.out.println(exception.getMessage());
        });
    }

    private void addCarUI() {
        System.out.println("Read car {id, name, horsepower, maxspeed}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer horsepower = Integer.valueOf(bufferRead.readLine());
            Integer maxspeed = Integer.valueOf(bufferRead.readLine());

            service.addCar(id, name, horsepower, maxspeed).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateCarUI() {
        System.out.println("Read updated car {id, name, experience, carid, teamid}");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Integer id = Integer.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Integer experience = Integer.valueOf(bufferRead.readLine());
            Integer carid = Integer.valueOf(bufferRead.readLine());
            Integer teamid = Integer.valueOf(bufferRead.readLine());
            service.updateDriver(id, name, experience, carid, teamid).whenComplete((status, exception) -> {
                if (exception == null)
                    System.out.println(status);
                else System.out.println(exception.getMessage());
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteCarUI() {
        System.out.println("Provide the id of the car you want to delete: ");
        Scanner reader = new Scanner(System.in);
        Integer id = reader.nextInt();
        this.service.deleteCar(id);
    }


}
