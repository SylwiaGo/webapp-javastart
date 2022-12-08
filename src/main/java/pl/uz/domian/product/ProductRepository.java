package pl.uz.domian.product;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByPromotedIsTrue();
    List<Product> findAllByCategory_NameIgnoreCase(String category);
}
