package guru.springframework.domain;

import guru.springframework.domain.security.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToMany
    @JoinTable
    // defaults to @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "user_id"),
    // inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if(!this.roles.contains(role)) {
            this.roles.add(role);
        }

        if(!role.getUsers().contains(this)) {
            role.getUsers().add(this);
        }
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

}
