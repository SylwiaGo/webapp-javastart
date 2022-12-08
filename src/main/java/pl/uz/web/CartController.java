package pl.uz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uz.domian.cart.Cart;
import pl.uz.domian.cart.CartService;
import pl.uz.domian.product.Product;
import pl.uz.domian.product.ProductRepository;
import pl.uz.domian.user.User;
import pl.uz.domian.user.UserService;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    private CartService cartService;
    private UserService userService;
    ProductRepository productRepository;

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    public CartController(CartService cartService, UserService userService, ProductRepository productRepository) {
        this.cartService = cartService;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserEmail = authentication.getName();
        Optional<User> userO = userService.findByEmail(currentUserEmail);
        User user = userO.orElseThrow();

        List<Cart> cartItems = cartService.findCartByUser(user);
        model.addAttribute("cartItems", cartItems);

        return "cart";
    }

    //dodawnanie produktow do koszyka
    @PostMapping("/cart/add/{pid}")
    public String addProductToCart(@PathVariable("pid") long productId,
                                   RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserEmail = authentication.getName();
        Optional<User> userO = userService.findByEmail(currentUserEmail);
        User user = userO.orElseThrow();

        Long idLong = Long.valueOf(productId);

        Optional<Product> ProductO = productRepository.findById(idLong);
        Product product = ProductO.orElseThrow();
        Long prd_id = product.getId();
        cartService.addProduct(prd_id, user);

        redirectAttributes.addFlashAttribute(
                NOTIFICATION_ATTRIBUTE,
                "Product  %s has been added to your cart".formatted(product.getName()));
        return "redirect:/product/"+prd_id.toString();
    }

    //usuwanie produktow z koszyka
    @PostMapping("cart/remove/{pid}")
    public String removeProductFromCart(@PathVariable("pid") long productId,
                                        RedirectAttributes redirectAttributes) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserEmail = authentication.getName();
        Optional<User> userO = userService.findByEmail(currentUserEmail);
        User user = userO.orElseThrow();

        Long idLong = Long.valueOf(productId);

        Optional<Product> ProductO = productRepository.findById(idLong);
        Product product = ProductO.orElseThrow();
        Long prd_id = product.getId();
        cartService.removeProduct(prd_id, user);

        redirectAttributes.addFlashAttribute(
                NOTIFICATION_ATTRIBUTE,
                "Product  %s has been removed from your cart".formatted(product.getName()));
        return "redirect:/cart";
    }
}
