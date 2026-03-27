package edson.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import edson.springframework.commands.ClientForm;
import edson.springframework.domain.Client;

/**
 * @author edson 16/01/2019
 */
@Component
public class ClientToClientForm implements Converter<Client, ClientForm> {
    @Override
    public ClientForm convert(Client client) {
        ClientForm clientForm = new ClientForm();
        clientForm.setId(client.getId());
        clientForm.setName(client.getName());

        return clientForm;
    }
}
