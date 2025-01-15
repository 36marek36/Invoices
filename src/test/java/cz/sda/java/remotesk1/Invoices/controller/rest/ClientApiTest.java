package cz.sda.java.remotesk1.Invoices.controller.rest;

import cz.sda.java.remotesk1.Invoices.model.Client;
import cz.sda.java.remotesk1.Invoices.service.ClientService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientApi.class)
public class ClientApiTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ClientService clientService;

    @Test
    void addClientShouldReturnCreatedClient() throws Exception {
        var client = new Client(UUID.randomUUID().toString(), "Marek", "Zvolen");

        Mockito.when(clientService.addClient(Mockito.any(String.class), Mockito.any(String.class))).thenReturn(client);

        mvc.perform(post("/rest/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Marek\",\"address\":\"Zvolen\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/clients/" + client.getId()))
                .andExpect(jsonPath("$.name").value("Marek"))
                .andExpect(jsonPath("$.address").value("Zvolen"));
    }

    @Test
    void getAllClientsShouldReturnAllClients() throws Exception {
        var client = new Client(UUID.randomUUID().toString(), "Marek", "Zvolen");

        Mockito.when(clientService.getAllClients()).thenReturn(List.of(client));

        mvc.perform(get("/rest/clients/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Marek"))
                .andExpect(jsonPath("$[0].address").value("Zvolen"));
    }

    @Test
    void getClientShouldReturnClient() throws Exception {
        var client = new Client(UUID.randomUUID().toString(), "Marek", "Zvolen");

        Mockito.when(clientService.getClient(Mockito.any(String.class))).thenReturn(client);

        mvc.perform(get("/rest/clients/" + client.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marek"))
                .andExpect(jsonPath("$.address").value("Zvolen"));
    }

    @Test
    void removeClientShouldReturnNoContent() throws Exception {
        var clientId = UUID.randomUUID().toString();

        Mockito.doNothing().when(clientService).removeClient(clientId);

        mvc.perform(delete("/rest/clients/{id}", clientId))
                .andExpect(status().isNoContent());

    }

    @Test
    void updateClientShouldReturnUpdatedClient() throws Exception {
        var clientId = UUID.randomUUID().toString();
        var client = new Client(clientId, "Marek", "Zvolen");

        Mockito.when(clientService.updateClient(Mockito.any(String.class), Mockito.any(Client.class))).thenReturn(client);

        mvc.perform(patch("/rest/clients/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Marek\",\"address\":\"Zvolen\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Marek"))
                .andExpect(jsonPath("$.address").value("Zvolen"));
    }

}
