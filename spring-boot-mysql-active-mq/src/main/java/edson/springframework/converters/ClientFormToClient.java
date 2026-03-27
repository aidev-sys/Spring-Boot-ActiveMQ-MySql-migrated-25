package edson.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edson.springframework.commands.ClientForm;
import edson.springframework.domain.Client;

/**
 * @author edson 16/01/2019
 */
@Component
public class ClientFormToClient implements Converter<ClientForm, Client> {

    @Override
    public Client convert(ClientForm clientForm) {
        Client client = new Client();
        if (clientForm.getId() != null) {
            client.setId(clientForm.getId());
        }
        client.setName(clientForm.getName());
        return client;
    }
}