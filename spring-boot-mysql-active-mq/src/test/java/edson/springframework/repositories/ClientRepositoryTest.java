package edson.springframework.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edson.springframework.domain.Client;
import edson.springframework.repositories.ClientRepository;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClientRepositoryTest {

    private static final String CLIENT_NAME= "a cool client";


    @Autowired
    private ClientRepository clientRepository;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testPersistence() {
        //given
        Client client = new Client();
        client.setName(CLIENT_NAME);

        //when
        clientRepository.save(client);

        //then
        Assert.assertNotNull(client.getId());
        Client newClient = clientRepository.findById(client.getId()).orElse(null);
        Assert.assertEquals((Long) 2L, newClient.getId());

    }
}