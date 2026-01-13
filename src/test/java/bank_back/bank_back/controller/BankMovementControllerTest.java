package bank_back.bank_back.controller;

import bank_back.bank_back.controller.webmodel.request.BankMovementRequest;
import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.model.MovementOrigin;
import bank_back.bank_back.domain.model.MovementType;
import bank_back.bank_back.domain.service.BankMovementService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import bank_back.bank_back.domain.mapper.BankMovementMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankMovementController.class)       
class BankMovementControllerTest {

        @MockitoBean
        private BankMovementService bankMovementService;

        @MockitoBean
        private BankMovementMapper bankMovementMapper;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        private BankMovement movement;
        private BankMovementDto movementDto;

        @BeforeEach
        void setUp() {
                movement = new BankMovement();
                movement.setId(1L);
                movement.setAmount(new BigDecimal("100.00"));

                movementDto = new BankMovementDto(1L, new BigDecimal("100.00"), MovementType.Add,
                                MovementOrigin.Transfer, "Concept", LocalDateTime.now(), null, null);
        }

        @Nested
        class GetAllMovementsTests {
                @Test
                void getAllMovements_ShouldReturnMovements() throws Exception {
                        List<BankMovement> movements = Collections.singletonList(movement);
                        when(bankMovementService.findAll()).thenReturn(movements);
                        when(bankMovementMapper.toDto(movement)).thenReturn(movementDto);

                        mockMvc.perform(get("/api/bank-movements"))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$[0].id").value(1))
                                        .andExpect(jsonPath("$[0].amount").value(100.0));
                }
        }

        @Nested
        class GetMovementByIdTests {
                @Test
                void getMovementById_ShouldReturnMovement_WhenExists() throws Exception {
                        when(bankMovementService.findById(1L)).thenReturn(movement);
                        when(bankMovementMapper.toDto(movement)).thenReturn(movementDto);

                        mockMvc.perform(get("/api/bank-movements/{id}", 1L))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.amount").value(100.0));
                }

                @Test
                void getMovementById_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        when(bankMovementService.findById(1L)).thenReturn(null);

                        mockMvc.perform(get("/api/bank-movements/{id}", 1L))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class CreateMovementTests {
                @Test
                void createMovement_ShouldReturnCreatedMovement() throws Exception {
                        BankMovementRequest request = new BankMovementRequest(
                                        null, new BigDecimal("100.00"), MovementType.Add, MovementOrigin.Transfer,
                                        "Concept",
                                        LocalDateTime.now(), null, null);

                        when(bankMovementMapper.toModel(any(BankMovementDto.class))).thenReturn(movement);
                        when(bankMovementService.create(any(BankMovement.class))).thenReturn(movement);
                        when(bankMovementMapper.toDto(movement)).thenReturn(movementDto);

                        mockMvc.perform(post("/api/bank-movements")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isCreated())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.amount").value(100.0));
                }
        }

        @Nested
        class UpdateMovementTests {
                @Test
                void updateMovement_ShouldReturnUpdatedMovement_WhenExists() throws Exception {
                        BankMovementRequest request = new BankMovementRequest(
                                        1L, new BigDecimal("100.00"), MovementType.Add, MovementOrigin.Transfer,
                                        "Concept", LocalDateTime.now(),
                                        null, null);

                        when(bankMovementMapper.toModel(any(BankMovementDto.class))).thenReturn(movement);
                        when(bankMovementService.update(eq(1L), any(BankMovement.class))).thenReturn(movement);
                        when(bankMovementMapper.toDto(movement)).thenReturn(movementDto);

                        mockMvc.perform(put("/api/bank-movements/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.amount").value(100.0));
                }

                @Test
                void updateMovement_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        BankMovementRequest request = new BankMovementRequest(
                                        1L, new BigDecimal("100.00"), MovementType.Add, MovementOrigin.Transfer,
                                        "Concept", LocalDateTime.now(),
                                        null, null);

                        when(bankMovementMapper.toModel(any(BankMovementDto.class))).thenReturn(movement);
                        when(bankMovementService.update(eq(1L), any(BankMovement.class))).thenReturn(null);

                        mockMvc.perform(put("/api/bank-movements/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class DeleteMovementByIdTests {
                @Test
                void deleteMovement_ShouldReturnNoContent() throws Exception {
                        doNothing().when(bankMovementService).delete(1L);

                        mockMvc.perform(delete("/api/bank-movements/{id}", 1L))
                                        .andExpect(status().isNoContent());
                }
        }
}
