package carRentalApp.business.service.customer;

import carRentalApp.business.dto.CustomerDTO;
import carRentalApp.data.entity.Customer;

import java.util.List;

public class CachingCustomerService implements CustomerService {

    private CustomerService origin;

    public CachingCustomerService(CustomerService origin){
        this.origin = origin;
    }

    @Override
    public List<Customer> getAll() {
        return origin.getAll();
    }

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        return origin.createCustomer(customerDTO);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        origin.deleteCustomer(customerDTO);
    }

    @Override
    public Customer getCustomer(CustomerDTO customerDTO) {
        return origin.getCustomer(customerDTO);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        origin.updateCustomer(customerDTO);
    }
}
