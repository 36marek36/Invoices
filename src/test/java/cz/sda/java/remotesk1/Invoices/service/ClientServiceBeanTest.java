package cz.sda.java.remotesk1.Invoices.service;


import cz.sda.java.remotesk1.Invoices.exception.NotFoundException;
import cz.sda.java.remotesk1.Invoices.model.Client;
import cz.sda.java.remotesk1.Invoices.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ClientServiceBeanTest {

    @Autowired
    ClientServiceBean clientServiceBean;

    @MockitoBean
    ClientRepository clientRepository;

    @TestConfiguration
    static class TestConfig {

        @Bean
        ClientServiceBean clientServiceBean(ClientRepository clientRepository) {
            return new ClientServiceBean(clientRepository);
        }
    }

    @Test
    void addClientShouldPass() {
        // given
        var uuid = UUID.randomUUID().toString();
        Mockito.when(clientRepository.save(Mockito.any(Client.class)))
                .thenAnswer(invocation -> {
                    Client client = invocation.getArgument(0);
                    client.setId(uuid); // Mock assigning an ID to the client
                    return client;
                });
        //when
        var client = clientServiceBean.addClient("Marek", "Zvolen");
        //then
        assert client.getName().equals("Marek");
        assert client.getAddress().equals("Zvolen");
    }

    @Test
    void removeClientShouldPass() {
        // given
        var uuid = UUID.randomUUID().toString();
        Mockito.when(clientRepository.existsById(Mockito.any(String.class)))
                .thenAnswer(invocation -> true);
        // when
        clientServiceBean.removeClient(uuid);
        // then
        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(Mockito.any(String.class));
    }

    @Test
    void removeClientShouldThrowNotFoundWhenClientDoesntExist() {
        // given
        var uuid = UUID.randomUUID().toString();
        Mockito.when(clientRepository.existsById(Mockito.any(String.class)))
                .thenAnswer(invocation -> false);
        // when
        Exception exception = Assertions.assertThrows(
                NotFoundException.class,
                () -> clientServiceBean.removeClient(uuid) // Code that should throw
        );

        Assertions.assertEquals("Client with id " + uuid + " does not exist", exception.getMessage());
    }

}

