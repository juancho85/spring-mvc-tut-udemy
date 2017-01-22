package guru.springframework.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by juancho on 22/01/2017.
 */
@Entity
public class User extends AbstractDomainEntity {

    private String username;

    //not stored into database
    @Transient
    private String password;

    private String encryptedPassword;

    private Boolean enabled = true;

    //when storing user, the customer will be merged as well
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Customer customer;

    //If user-cart link is broken, the cart entity will be deleted
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        //We make the relation bidirectional
        customer.setUser(this);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

}
