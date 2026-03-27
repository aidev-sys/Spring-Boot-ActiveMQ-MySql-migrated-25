package edson.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import edson.springframework.domain.Client;

/**
 * @author edson 16/01/2019
 */
public interface ClientRepository extends CrudRepository<Client, Long> {
}
