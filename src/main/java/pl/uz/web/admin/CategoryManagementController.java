package pl.uz.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.uz.domian.category.CategoryService;
import pl.uz.domian.category.dto.CategoryDto;

@Controller
public class CategoryManagementController {
    private final CategoryService categoryService;


    public CategoryManagementController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/add-category")
    public String addCategoryForm(Model model) {
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    @PostMapping("/admin/add-category")
    public String addCategory(CategoryDto category, RedirectAttributes redirectAttributes) {
        if (category.getName() == null || category.getName().isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "Category has to have name");
        } else {
            categoryService.addCategory(category);
            redirectAttributes.addFlashAttribute(
                    AdminController.NOTIFICATION_ATTRIBUTE,
                    "Category %s has been added".formatted(category.getName()));
        }
        return "redirect:/admin";
    }
}
