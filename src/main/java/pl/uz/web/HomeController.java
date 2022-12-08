package pl.uz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.uz.domian.product.ProductService;
import pl.uz.domian.product.dto.ProductDto;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<ProductDto> promotedProducts = productService.findAllPromotedProducts();
        model.addAttribute("heading", "Promoted products");
        model.addAttribute("description", "Products highly recommend by our team");
        model.addAttribute("products", promotedProducts);
        return "product-listing";
    }
}
