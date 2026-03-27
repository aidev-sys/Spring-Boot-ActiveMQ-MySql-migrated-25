package edson.springframework.services;

import java.util.List;

import edson.springframework.commands.ClientForm;
import edson.springframework.domain.Client;

/**
 * @author edson 16/01/2019
 */
public interface ClientService {

    List<Client> listAll();

    Client getById(Long id);

    Client saveOrUpdate(Client client);

    void delete(Long id);

    Client saveOrUpdateClientForm(ClientForm clientForm);

    void sendMessage(String id);
}
