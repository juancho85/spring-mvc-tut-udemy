package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by juancho on 18/01/2017.
 */
@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    public CustomerServiceImpl() {
        loadDomainObjects();
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    protected void loadDomainObjects() {
        domainMap = new HashMap<>();
        List<Customer> customerList = IntStream.range(1, 5).mapToObj( i ->
        new Customer(i, "name"+i, "Last name "+i, "email@"+i, "+3400000"+i, "address L1 "+i, "address L2 "+i, "city"+i, "state"+i, "zip"+i)
        ).collect(Collectors.toList());

        domainMap =
                IntStream.range(0,customerList.size())
                        .boxed()
                        .collect(Collectors.toMap (i -> i, i -> customerList.get(i)));
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
