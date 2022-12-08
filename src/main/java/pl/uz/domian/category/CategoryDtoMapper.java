package pl.uz.domian.category;

import pl.uz.domian.category.dto.CategoryDto;

public class CategoryDtoMapper {
    static CategoryDto map(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
