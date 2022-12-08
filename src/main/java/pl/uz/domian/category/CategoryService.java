package pl.uz.domian.category;

import org.springframework.stereotype.Service;
import pl.uz.domian.category.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<CategoryDto> findCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name).map(CategoryDtoMapper::map);
    }

    public List<CategoryDto> findAllCategories() {
        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false).map(CategoryDtoMapper::map).toList();
    }

    public void addCategory(CategoryDto category) {
        Category categoryToSave = new Category();
        categoryToSave.setName(category.getName());
        categoryToSave.setDescription(category.getDescription());
        categoryRepository.save(categoryToSave);
    }
}
