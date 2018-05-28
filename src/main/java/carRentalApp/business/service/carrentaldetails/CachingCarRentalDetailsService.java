package carRentalApp.business.service.carrentaldetails;

import carRentalApp.business.dto.CarRentalDetailsDTO;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.CarRentalDetails;

import java.io.IOException;

public class CachingCarRentalDetailsService implements CarRentalDetailsService{

    public CarRentalDetailsService origin;

    public CachingCarRentalDetailsService(CarRentalDetailsService origin){
        this.origin=origin;
    }


    @Override
    public CarRentalDetails createCarRentalDetails(CarRentalDetailsDTO carRentalDetailsDTO) {
        return origin.createCarRentalDetails(carRentalDetailsDTO);
    }

    @Override
    public CarRentalDetails findByCar(Car car) {
        return origin.findByCar(car);
    }

    @Override
    public double calculateDistance(String startingLocation, String finalLocation) throws IOException {
        return origin.calculateDistance(startingLocation,finalLocation);
    }

    @Override
    public String generateEmailContent(CarRentalDetailsDTO carRentalDetailsDTO) throws IOException {
        return origin.generateEmailContent(carRentalDetailsDTO);
    }

    @Override
    public void generateEmail(String content) {
        origin.generateEmail(content);
    }
}
