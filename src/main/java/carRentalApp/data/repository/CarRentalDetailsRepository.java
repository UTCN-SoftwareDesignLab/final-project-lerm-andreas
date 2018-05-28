package carRentalApp.data.repository;

import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.CarRentalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRentalDetailsRepository extends JpaRepository<CarRentalDetails,Long> {

    CarRentalDetails findByCar(Car car);


}
