package guru.springframework.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by juancho on 22/01/2017.
 */
@Entity
public class Cart extends AbstractDomainEntity {

    @OneToOne
    private User user;

    //mappedBy: To use a foreign key instead of using an association table
    //orphan removal: If the cart is deleted, so will be the cartDetails
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", orphanRemoval = true)
    private List<CartDetail> cartDetails = new ArrayList<>();

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addCartDetail(CartDetail cartDetail) {
        cartDetails.add(cartDetail);
        //bidirectional relationship
        cartDetail.setCart(this);
    }

    public void removeCartDetail(CartDetail cartDetail) {
        cartDetail.setCart(null);
        //bidirectional relationship
        this.cartDetails.remove(cartDetail);
    }


}
