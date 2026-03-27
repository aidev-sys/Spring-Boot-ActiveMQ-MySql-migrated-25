package edson.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import edson.springframework.domain.Product;

/**
 * @author edson 16/01/2019
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
