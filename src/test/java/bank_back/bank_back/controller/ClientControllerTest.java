package bank_back.bank_back.controller;

import bank_back.bank_back.controller.webmodel.request.ClientRequest;
import bank_back.bank_back.domain.dto.ClientDto;
import bank_back.bank_back.domain.model.Client;
import bank_back.bank_back.domain.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import bank_back.bank_back.domain.mapper.ClientMapper;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

        @MockitoBean
        private ClientService clientService;

        @MockitoBean
        private ClientMapper clientMapper;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        private Client client;
        private ClientDto clientDto;

        @BeforeEach
        void setUp() {
                client = new Client();
                client.setId(1L);
                client.setLogin("testuser");

                clientDto = new ClientDto(1L, "testuser", "password", "John", "Doe", "Smith", "12345678A", "token",
                                null);
        }

        @Nested
        class GetAllClientsTests {
                @Test
                void getAllClients_ShouldReturnClients() throws Exception {
                        List<Client> clients = Collections.singletonList(client);
                        when(clientService.findAll()).thenReturn(clients);
                        when(clientMapper.toDto(client)).thenReturn(clientDto);

                        mockMvc.perform(get("/api/clients"))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$[0].id").value(1))
                                        .andExpect(jsonPath("$[0].login").value("testuser"));
                }
        }

        @Nested
        class GetClientByIdTests {
                @Test
                void getClientById_ShouldReturnClient_WhenExists() throws Exception {
                        when(clientService.findById(1L)).thenReturn(client);
                        when(clientMapper.toDto(client)).thenReturn(clientDto);

                        mockMvc.perform(get("/api/clients/{id}", 1L))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.login").value("testuser"));
                }

                @Test
                void getClientById_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        when(clientService.findById(1L)).thenReturn(null);

                        mockMvc.perform(get("/api/clients/{id}", 1L))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class CreateClientTests {
                @Test
                void createClient_ShouldReturnCreatedClient() throws Exception {
                        ClientRequest request = new ClientRequest(
                                        null, "testuser", "password", "John", "Doe", "Smith", "12345678A", "token");

                        when(clientMapper.toModel(any(ClientDto.class))).thenReturn(client);
                        when(clientService.create(any(Client.class))).thenReturn(client);
                        when(clientMapper.toDto(client)).thenReturn(clientDto);

                        mockMvc.perform(post("/api/clients")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isCreated())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.login").value("testuser"));
                }
        }

        @Nested
        class UpdateClientTests {
                @Test
                void updateClient_ShouldReturnUpdatedClient_WhenExists() throws Exception {
                        ClientRequest request = new ClientRequest(
                                        1L, "testuser", "password", "John", "Doe", "Smith", "12345678A", "token");

                        when(clientMapper.toModel(any(ClientDto.class))).thenReturn(client);
                        when(clientService.update(eq(1L), any(Client.class))).thenReturn(client);
                        when(clientMapper.toDto(client)).thenReturn(clientDto);

                        mockMvc.perform(put("/api/clients/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.login").value("testuser"));
                }

                @Test
                void updateClient_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        ClientRequest request = new ClientRequest(
                                        1L, "testuser", "password", "John", "Doe", "Smith", "12345678A", "token");

                        when(clientMapper.toModel(any(ClientDto.class))).thenReturn(client);
                        when(clientService.update(eq(1L), any(Client.class))).thenReturn(null);

                        mockMvc.perform(put("/api/clients/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class DeleteClientByIdTests {
                @Test
                void deleteClient_ShouldReturnNoContent() throws Exception {
                        doNothing().when(clientService).delete(1L);

                        mockMvc.perform(delete("/api/clients/{id}", 1L))
                                        .andExpect(status().isNoContent());
                }
        }
}
