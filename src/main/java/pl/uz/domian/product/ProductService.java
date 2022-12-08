package pl.uz.domian.product;

import org.springframework.stereotype.Service;
import pl.uz.domian.category.Category;
import pl.uz.domian.category.CategoryRepository;
import pl.uz.domian.product.dto.ProductDto;
import pl.uz.domian.product.dto.ProductSaveDto;
import pl.uz.storage.FileStorageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, FileStorageService fileStorageService) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.fileStorageService = fileStorageService;
    }

    public List<ProductDto> findAllPromotedProducts() {
        return productRepository.findAllByPromotedIsTrue()
                .stream()
                .map(ProductDtoMapper::map)
                .toList();
    }

    public Optional<ProductDto> findProductById(long id) {
        return productRepository.findById(id).map(ProductDtoMapper::map);
    }

    public List<ProductDto> findProductByCategoryName(String category) {
        return productRepository.findAllByCategory_NameIgnoreCase(category).stream()
                .map(ProductDtoMapper::map)
                .toList();
    }

    public void addProduct(ProductSaveDto productToSave) {
        Product product = new Product();
        product.setName(productToSave.getName());
        product.setPrice(productToSave.getPrice());
        product.setProducer(productToSave.getProducer());
        product.setShortDescription(productToSave.getShortDescription());
        product.setDescription(productToSave.getDescription());

        Category category = categoryRepository.findByNameIgnoreCase(productToSave.getCategory()).orElseThrow();
        product.setCategory(category);
//        jesli uzytkownik nie doda zdjecie to nic sie nie zapisze w folderze img i bedzie wyswietlane zdjecie domyslne "placeholder"
        if (!productToSave.getImage().isEmpty()) {
            String savedFileName = fileStorageService.saveImage(productToSave.getImage());
            product.setImage(savedFileName);
        }
        productRepository.save(product);
    }
}
