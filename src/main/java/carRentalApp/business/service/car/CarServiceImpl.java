package carRentalApp.business.service.car;

import carRentalApp.business.dto.CarDTO;
import carRentalApp.data.entity.Car;
import carRentalApp.data.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(final CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Car createCar(CarDTO carDTO) {

        Car car = new Car(carDTO.getVin(),carDTO.getBrand(),carDTO.getModel(),"yes","just added",carDTO.getImage());
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(CarDTO carDTO) {
        Car car = carRepository.findByBrandAndAndModel(carDTO.getBrand(),carDTO.getModel());
        carRepository.delete(car);
    }

    @Override
    public Car getCar(CarDTO carDTO) {
        return carRepository.findByBrandAndAndModel(carDTO.getBrand(),carDTO.getModel());
    }

    @Override
    public void updateCar(CarDTO carDTO) {

    }

    @Override
    public void changeAvailableStatus(CarDTO carDTO) {
        carRepository.changeAvailableStatus(carDTO.getAvailable(),carDTO.getVin());
    }

    @Override
    public void changeReturnedStatus(CarDTO carDTO){
        carRepository.changeReturnedStatus(carDTO.getReturned(),carDTO.getVin());
    }

    @Override
    public Car getCarByVin(String vin) {
        return carRepository.findByVin(vin);
    }
}
