
package carRentalApp;
import carRentalApp.Messages.TLSEmail;
import carRentalApp.business.service.car.CachingCarService;
import carRentalApp.business.service.car.CarService;
import carRentalApp.business.service.car.CarServiceImpl;
import carRentalApp.business.service.carrentaldetails.CachingCarRentalDetailsService;
import carRentalApp.business.service.carrentaldetails.CarRentalDetailsService;
import carRentalApp.business.service.carrentaldetails.CarRentalDetailsServiceImpl;
import carRentalApp.business.service.customer.CachingCustomerService;
import carRentalApp.business.service.customer.CustomerService;
import carRentalApp.business.service.customer.CustomerServiceImpl;
import carRentalApp.business.service.user.CachingUserService;
import carRentalApp.business.service.user.UserService;
import carRentalApp.business.service.user.UserServiceImpl;
import carRentalApp.data.entity.CarRentalDetails;
import carRentalApp.data.repository.CarRentalDetailsRepository;
import carRentalApp.data.repository.CarRepository;
import carRentalApp.data.repository.CustomerRepository;
import carRentalApp.data.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "UserService")
    public UserService userService(UserRepository repository) {
        return new CachingUserService(new UserServiceImpl(repository));
    }

    @Bean(name = "CarService")
    public CarService CarService(CarRepository repository) {
        return new CachingCarService(new CarServiceImpl(repository));
    }

    @Bean(name = "CustomerService")
    public CustomerService CustomerService(CarRepository carRepository, CustomerRepository customerRepository) {
        return new CachingCustomerService(new CustomerServiceImpl(customerRepository,carRepository));
    }

    @Bean(name ="CarRentalDetailsService")
    public CarRentalDetailsService CarRentalDetailsService(CarRentalDetailsRepository carRentalDetailsRepository, CarRepository carRepository, CustomerRepository customerRepository){
        return new CachingCarRentalDetailsService(new CarRentalDetailsServiceImpl(carRentalDetailsRepository,carRepository,customerRepository));
    }

}

