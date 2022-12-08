package pl.uz.domian.cart;

import org.springframework.stereotype.Service;
import pl.uz.domian.product.Product;
import pl.uz.domian.product.ProductRepository;
import pl.uz.domian.user.User;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<Cart> findCartByUser(User user) {
        return cartRepository.findByUser(user);
    }

    // dodawanie produktow do koszyka - narazie mozliwe jest dodanie tylko jednej sztuki
    public Integer addProduct(Long productId, User user) {
        Integer addedQuantity = 1;

        Product product = productRepository.findById(productId).get();

        Cart cart = cartRepository.findByUserAndProduct(user, product);

        if(cart != null) {
            addedQuantity = cart.getQuantity() + addedQuantity;
            cart.setQuantity(addedQuantity);
        } else {
            cart = new Cart();
            cart.setQuantity(addedQuantity);
            cart.setUser_id(user);
            cart.setProduct_id(product);
        }
        cartRepository.save(cart);
        return addedQuantity;
    }

    // dodawanie produktow do koszyka - narazie mozliwe jest usuwanie tylko jednej sztuki
    public Integer removeProduct(Long productId, User user) {
        Integer removedQuantity = 1;

        Product product = productRepository.findById(productId).get();

        Cart cart = cartRepository.findByUserAndProduct(user, product);
        cartRepository.delete(cart);
        return removedQuantity;
    }
}
