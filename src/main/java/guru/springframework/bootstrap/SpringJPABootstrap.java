package guru.springframework.bootstrap;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by juancho on 21/01/2017.
 */
@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    public void loadProducts() {
        Product product1= new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http:example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2= new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http:example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3= new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("32.99"));
        product3.setImageUrl("http:example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4= new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http:example.com/product4");
        productService.saveOrUpdate(product4);
    }

    public void loadCustomers() {
        List<Customer> customerList = IntStream.range(1, 5).mapToObj(i ->
                new Customer(i, "name"+i, "Last name "+i, "email@"+i, "+3400000"+i, "address L1 "+i, "address L2 "+i, "city"+i, "state"+i, "zip"+i)
        ).collect(Collectors.toList());

        for(Customer customer : customerList) {
            customerService.saveOrUpdate(customer);
        }

    }
}
