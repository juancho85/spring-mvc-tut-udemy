package guru.springframework.services;

import guru.springframework.domain.Customer;

import java.util.List;

/**
 * Created by juancho on 18/01/2017.
 */
public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    void deleteCustomer(Integer id);

    Customer saveOrUpdateCustomer(Customer customer);

}
