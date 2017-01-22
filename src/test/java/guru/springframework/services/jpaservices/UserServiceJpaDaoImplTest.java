package guru.springframework.services.jpaservices;


import guru.springframework.domain.*;
import guru.springframework.services.ProductService;
import guru.springframework.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by juancho on 22/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private ProductService productService;

    private UserService userService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Juan");
        customer.setLastName("Sanchez");
        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getEncryptedPassword());
        assertNotNull(savedUser.getCustomer());
        assertNotNull(savedUser.getCustomer().getId());

    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {

        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Chase");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCustomer());
        assertNotNull(savedUser.getCustomer().getId());

    }

    @Test
    public void testAddCartToUser() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
    }

    @Test
    public void testAddCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
        assertEquals(savedUser.getCart().getCartDetails().size(),2);
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() throws Exception {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assertEquals(savedUser.getCart().getCartDetails().size(),2);

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

        userService.saveOrUpdate(savedUser);

        assertEquals(savedUser.getCart().getCartDetails().size(),1);
    }
}
