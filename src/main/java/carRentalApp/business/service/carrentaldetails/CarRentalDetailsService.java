package carRentalApp.business.service.carrentaldetails;

import carRentalApp.business.dto.CarRentalDetailsDTO;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.CarRentalDetails;

import java.io.IOException;

public interface CarRentalDetailsService {

    CarRentalDetails createCarRentalDetails(CarRentalDetailsDTO carRentalDetailsDTO);
    CarRentalDetails findByCar(Car car);
    double calculateDistance(String startingLocation,String finalLocation) throws IOException;
    String generateEmailContent(CarRentalDetailsDTO carRentalDetailsDTO) throws IOException;
    void generateEmail(String content);
}
