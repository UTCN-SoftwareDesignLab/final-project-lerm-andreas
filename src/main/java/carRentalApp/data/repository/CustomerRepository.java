package carRentalApp.data.repository;

import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByIdentityCardNumber(String identityCardNumber);

    @Modifying
    @Transactional
    @Query("update Customer c set c.car=?1 where c.identityCardNumber = ?2")
    void updateCustomer(Car car, String identityCardNumber);

}
