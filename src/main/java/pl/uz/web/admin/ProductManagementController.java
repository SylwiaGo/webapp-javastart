package pl.uz.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uz.domian.category.CategoryService;
import pl.uz.domian.category.dto.CategoryDto;
import pl.uz.domian.product.ProductService;
import pl.uz.domian.product.dto.ProductSaveDto;

import java.util.List;

@Controller
public class ProductManagementController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductManagementController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/add-product")
    public String addProductForm(Model model) {
        List<CategoryDto> allCategories = categoryService.findAllCategories();
        model.addAttribute("categories", allCategories);
        ProductSaveDto product = new ProductSaveDto();
        model.addAttribute("product", product);
        return "admin/product-form";
    }

    @PostMapping("/admin/add-product")
    public String addProduct(ProductSaveDto product, RedirectAttributes redirectAttributes) {
        productService.addProduct(product);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Product  %s has been added".formatted(product.getName()));
        return "redirect:/admin";
    }
}
