package guru.springframework.services.jpaservices;


import guru.springframework.domain.Customer;
import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by juancho on 22/01/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("jpadao")
public class UserServiceJpaDaoImplTest {

    private UserService userService;

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
}
