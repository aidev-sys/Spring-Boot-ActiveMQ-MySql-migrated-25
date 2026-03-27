package edson.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edson.springframework.domain.Product;

/**
 * @author edson 16/01/2019
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}