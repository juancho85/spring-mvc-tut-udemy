package guru.springframework.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by juancho on 22/01/2017.
 */
@Entity
public class OrderLine extends AbstractDomainEntity {

    @ManyToOne
    private Order order;

    @OneToOne
    private Product product;

    private Integer quantity;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
