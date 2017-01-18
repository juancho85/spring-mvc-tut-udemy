package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by juancho on 18/01/2017.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private Map<Integer, Customer> customers = new HashMap<>();

    public CustomerServiceImpl() {
        loadCustomers();
    }

    public void loadCustomers() {
        List<Customer> customerList = IntStream.range(1, 5).mapToObj( i ->
        new Customer(i, "name"+i, "Last name "+i, "email@"+i, "+3400000"+i, "address L1 "+i, "address L2 "+i, "city"+i, "state"+i, "zip"+i)
        ).collect(Collectors.toList());

        customers =
                IntStream.range(0,customerList.size())
                        .boxed()
                        .collect(Collectors.toMap (i -> i, i -> customerList.get(i)));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    @Override
    public Customer getCustomerById(Integer id) {
        return customers.get(id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customers.remove(id);
    }

    @Override
    public Customer saveOrUpdateCustomer(Customer customer) {
        if(customer != null && customer.getId()!=null){
            //update
            customers.put(customer.getId(), customer);
        } else {
            Integer nextKey = getNextKey();
            customer.setId(nextKey);
            customers.put(nextKey, customer);
        }

        return customer;
    }

    private Integer getNextKey() {
        return Collections.max(customers.keySet()) + 1;
    }
}
