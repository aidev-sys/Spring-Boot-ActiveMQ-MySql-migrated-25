package edson.springframework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import edson.springframework.domain.Client;

/**
 * @author edson 16/01/2019
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}