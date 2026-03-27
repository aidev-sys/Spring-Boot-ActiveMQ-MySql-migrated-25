package edson.springframework.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import edson.springframework.SpringBootActiveMQApplication;
import edson.springframework.commands.ClientForm;
import edson.springframework.converters.ClientFormToClient;
import edson.springframework.domain.Client;
import edson.springframework.repositories.ClientRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author edson 16/01/2019
 */
@Service
public class ClientServiceImpl implements ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientServiceImpl.class);

    private ClientRepository clientRepository;
    private ClientFormToClient clientFormToClient;
    private JmsTemplate jmsTemplate;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientFormToClient clientFormToClient,
                              JmsTemplate jmsTemplate) {
        this.clientRepository = clientRepository;
        this.clientFormToClient = clientFormToClient;
        this.jmsTemplate = jmsTemplate;
    }


    @Override
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        clientRepository.findAll().forEach(clients::add); //fun with Java 8
        return clients;
    }

    @Override
    public Client getById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client saveOrUpdate(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);

    }

    @Override
    public Client saveOrUpdateClientForm(ClientForm clientForm) {
        Client savedClient = saveOrUpdate(clientFormToClient.convert(clientForm));

        System.out.println("Saved Client Id: " + savedClient.getId());
        return savedClient;
    }

    @Override
    public void sendMessage(String id) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", id);
        log.info("Sending the index request through queue message");
        jmsTemplate.convertAndSend(SpringBootActiveMQApplication.CLIENT_MESSAGE_QUEUE, actionmap);
    }
}
