package carRentalApp.business.service.customer;

import carRentalApp.business.dto.CustomerDTO;
import carRentalApp.data.entity.Customer;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerService {

    List<Customer> getAll();
    @Transactional
    Customer createCustomer(CustomerDTO customerDTO);
    @Transactional
    void deleteCustomer(CustomerDTO customerDTO);

    Customer getCustomer(CustomerDTO customerDTO);

    @Transactional
    void updateCustomer(CustomerDTO customerDTO);


}
