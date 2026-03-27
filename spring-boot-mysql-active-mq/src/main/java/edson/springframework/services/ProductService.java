package edson.springframework.services;

import java.util.List;

import edson.springframework.commands.ProductForm;
import edson.springframework.domain.Product;

/**
 * @author edson 16/01/2019
 */
public interface ProductService {

    List<Product> listAll();

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);

    void sendMessage(String id);
}
