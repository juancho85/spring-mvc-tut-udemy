package guru.springframework.services;

import guru.springframework.domain.Product;

import java.util.List;

/**
 * Created by juancho on 13/01/2017.
 */
public interface ProductService {

    List<Product> listAllProducts();

    Product getProductById(Integer id);
}

