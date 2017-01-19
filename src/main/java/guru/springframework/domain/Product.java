package guru.springframework.domain;

import java.math.BigDecimal;

/**
 * Created by juancho on 13/01/2017.
 */
public class Product implements DomainObject {
    private Integer id;
    private String description;
    private BigDecimal price;
    private String imageUrl;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
