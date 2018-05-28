package carRentalApp.business.service.car;

import carRentalApp.business.dto.CarDTO;
import carRentalApp.business.dto.UserDTO;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface CarService {

    List<Car> getAll();
    @Transactional
    Car createCar(CarDTO carDTO);
    @Transactional
    void deleteCar(CarDTO carDTO);

    Car getCar(CarDTO carDTO);

    @Transactional
    void updateCar(CarDTO carDTO);

    @Transactional
    void changeAvailableStatus(CarDTO carDTO);

    @Transactional
    void changeReturnedStatus(CarDTO carDTO);

    Car getCarByVin(String vin);

}
