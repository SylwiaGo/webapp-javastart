package pl.uz.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.uz.domian.category.CategoryService;
import pl.uz.domian.category.dto.CategoryDto;
import pl.uz.domian.product.ProductService;
import pl.uz.domian.product.dto.ProductDto;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("category/{name}")
    public String getCategory(@PathVariable String name, Model model) {
        CategoryDto category = categoryService.findCategoryByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<ProductDto> products = productService.findProductByCategoryName(name);
        model.addAttribute("heading", category.getName());
        model.addAttribute("description", category.getDescription());
        model.addAttribute("products", products);
        return "product-listing";
    }

    @GetMapping("categories")
    public String getCategoryList(Model model) {
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "category-listing";
    }
}
