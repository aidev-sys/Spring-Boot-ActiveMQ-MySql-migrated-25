package edson.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edson.springframework.commands.ProductForm;
import edson.springframework.domain.Product;

/**
 * @author edson 16/01/2019
 */
@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }
}
