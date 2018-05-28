package carRentalApp.business.service.customer;

import carRentalApp.business.dto.CustomerDTO;
import carRentalApp.data.entity.Car;
import carRentalApp.data.entity.Customer;
import carRentalApp.data.repository.CarRepository;
import carRentalApp.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CarRepository carRepository;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository, final CarRepository carRepository){
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        Car car = carRepository.findByBrandAndAndModel(customerDTO.getCarBrand(),customerDTO.getCarModel());
        Customer customer = new Customer(customerDTO.getName(),customerDTO.getIdentityCardNumber(),customerDTO.getEmail(),customerDTO.getDateOfBirth(),customerDTO.getAddress(),car);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findByIdentityCardNumber(customerDTO.getIdentityCardNumber());
        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomer(CustomerDTO customerDTO) {
        return customerRepository.findByIdentityCardNumber(customerDTO.getIdentityCardNumber());
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        Car car = carRepository.findByBrandAndAndModel(customerDTO.getCarBrand(),customerDTO.getCarModel());
        String identityCardNumber = customerDTO.getIdentityCardNumber();

        customerRepository.updateCustomer(car,identityCardNumber);
    }
}
