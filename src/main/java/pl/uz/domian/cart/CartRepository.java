package pl.uz.domian.cart;

import org.springframework.data.repository.CrudRepository;
import pl.uz.domian.product.Product;
import pl.uz.domian.user.User;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    public List<Cart> findByUser(User user);

    public Cart findByUserAndProduct(User user, Product product);

}
