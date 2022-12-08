package pl.uz.domian.product;

import pl.uz.domian.product.dto.ProductDto;

public class ProductDtoMapper {
    static ProductDto map(Product product) {
        return new ProductDto(
        product.getId(),
        product.getName(),
        product.getPrice(),
        product.getCategory().getName(),
        product.getProducer(),
        product.isPromoted(),
        product.getShortDescription(),
        product.getDescription(),
        product.getImage()
        );
    }
}
