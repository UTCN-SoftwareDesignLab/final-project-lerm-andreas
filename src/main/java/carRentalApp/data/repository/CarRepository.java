package carRentalApp.data.repository;


import carRentalApp.data.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CarRepository extends JpaRepository<Car,Integer>{

    Car findByBrandAndAndModel(String brand, String model);

    Car findByVin(String vin);

    @Modifying
    @Transactional
    @Query("update Car c set c.available= ?1 where c.vin = ?2")
    void changeAvailableStatus(String available, String vin);

    @Modifying
    @Transactional
    @Query("update Car c set c.returned= ?1 where c.vin = ?2")
    void changeReturnedStatus(String returned, String vin);


}
