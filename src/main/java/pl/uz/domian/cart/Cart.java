package pl.uz.domian.cart;

import pl.uz.domian.product.Product;
import pl.uz.domian.user.User;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public User getUser_id() {
        return user;
    }

    public Product getProduct_id() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser_id(User user_id) {
        this.user = user_id;
    }

    public void setProduct_id(Product product_id) {
        this.product = product_id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
