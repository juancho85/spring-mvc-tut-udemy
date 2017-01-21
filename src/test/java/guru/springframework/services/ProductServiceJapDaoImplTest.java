package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by juancho on 21/01/2017.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class ProductServiceJapDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testListAll() throws Exception {
        List<Product> products = (List<Product>) productService.listAll();
        assertEquals(products.size(), 4);
    }

//    @Test
//    public void getById() throws Exception {
//
//    }
//
//    @Test
//    public void saveOrUpdate() throws Exception {
//
//    }
//
//    @Test
//    public void delete() throws Exception {
//
//    }

}