package server.service;

import common.domain.Car;
import common.domain.Driver;
import server.repository.DBpersistence.DriverDBRepository;
import server.repository.DBpersistence.Repository;

public class CarService {
    private Repository<Integer, Car> carRepository;
    private Repository<Integer, Driver> driverRepository;

    public CarService(Repository<Integer, Car> carRepository, Repository<Integer, Driver> driverRepository) {
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    public void addCar(Integer id, String name, Integer horsepower, Integer maxspeed) {
        Car car = new Car(id, name, horsepower, maxspeed);
        this.carRepository.save(car);
    }

    public void deleteCar(Integer id) {
        this.carRepository.delete(id);
    }

    public void updateCar(Integer id, String name, Integer horsepower, Integer maxspeed) {
        Car car = new Car(id, name, horsepower, maxspeed);
        this.carRepository.update(car);
    }

    public Iterable<Driver> getCarDriver (Integer id) {
        Iterable<Driver> carDriver = null;
        if(this.driverRepository instanceof DriverDBRepository){
            carDriver = ((DriverDBRepository)driverRepository).getDriversCar(id);
        }
        return carDriver;
    }
}
