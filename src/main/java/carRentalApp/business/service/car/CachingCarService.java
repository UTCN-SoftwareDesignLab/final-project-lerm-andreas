package carRentalApp.business.service.car;

import carRentalApp.business.dto.CarDTO;
import carRentalApp.data.entity.Car;

import java.util.List;

public class CachingCarService implements CarService {

    private CarService origin;

    public CachingCarService(CarService origin){
        this.origin=origin;
    }


    @Override
    public List<Car> getAll() {
        return origin.getAll();
    }

    @Override
    public Car createCar(CarDTO carDTO) {
        return origin.createCar(carDTO);
    }

    @Override
    public void deleteCar(CarDTO carDTO) {
        origin.deleteCar(carDTO);
    }

    @Override
    public Car getCar(CarDTO carDTO) {
        return origin.getCar(carDTO);
    }

    @Override
    public void updateCar(CarDTO carDTO) {
        origin.updateCar(carDTO);
    }

    @Override
    public void changeAvailableStatus(CarDTO carDTO) {
        origin.changeAvailableStatus(carDTO);
    }

    @Override
    public void changeReturnedStatus(CarDTO carDTO) {
        origin.changeReturnedStatus(carDTO);
    }

    @Override
    public Car getCarByVin(String vin) {
        return origin.getCarByVin(vin);
    }
}
