package edson.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import edson.springframework.commands.ProductForm;
import edson.springframework.domain.Product;

/**
 * @author edson 16/01/2019
 */
@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

    @Override
    public Product convert(ProductForm productForm) {
        Product product = new Product();
        if (productForm.getId() != null  && !StringUtils.isEmpty(productForm.getId())) {
            product.setId(new Long(productForm.getId()));
        }
        product.setDescription(productForm.getDescription());
        product.setPrice(productForm.getPrice());
        product.setImageUrl(productForm.getImageUrl());
        return product;
    }
}
