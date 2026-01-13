package bank_back.bank_back.controller;

import bank_back.bank_back.controller.webmodel.request.CreditCardRequest;
import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import bank_back.bank_back.domain.mapper.CreditCardMapper;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditCardController.class)
class CreditCardControllerTest {

        @MockitoBean
        private CreditCardService creditCardService;

        @MockitoBean
        private CreditCardMapper creditCardMapper;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        private CreditCard card;
        private CreditCardDto cardDto;

        @BeforeEach
        void setUp() {
                card = new CreditCard();
                card.setId(1L);
                card.setNumber("1234567890123456");

                cardDto = new CreditCardDto(1L, "1234567890123456", LocalDate.of(2030, 1, 1), 123, "John Doe");
        }

        @Nested
        class GetAllCardsTests {
                @Test
                void getAllCards_ShouldReturnCards() throws Exception {
                        List<CreditCard> cards = Collections.singletonList(card);
                        when(creditCardService.findAll()).thenReturn(cards);
                        when(creditCardMapper.toDto(card)).thenReturn(cardDto);

                        mockMvc.perform(get("/api/credit-cards"))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$[0].id").value(1))
                                        .andExpect(jsonPath("$[0].number").value("1234567890123456"));
                }
        }

        @Nested
        class GetCardByIdTests {
                @Test
                void getCardById_ShouldReturnCard_WhenExists() throws Exception {
                        when(creditCardService.findById(1L)).thenReturn(card);
                        when(creditCardMapper.toDto(card)).thenReturn(cardDto);

                        mockMvc.perform(get("/api/credit-cards/{id}", 1L))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.number").value("1234567890123456"));
                }

                @Test
                void getCardById_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        when(creditCardService.findById(1L)).thenReturn(null);

                        mockMvc.perform(get("/api/credit-cards/{id}", 1L))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class CreateCardTests {
                @Test
                void createCard_ShouldReturnCreatedCard() throws Exception {
                        CreditCardRequest request = new CreditCardRequest(
                                        null, "1234567890123456", LocalDate.of(2030, 1, 1), 123, "John Doe");

                        when(creditCardMapper.toModel(any(CreditCardDto.class))).thenReturn(card);
                        when(creditCardService.create(any(CreditCard.class))).thenReturn(card);
                        when(creditCardMapper.toDto(card)).thenReturn(cardDto);

                        mockMvc.perform(post("/api/credit-cards")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isCreated())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.number").value("1234567890123456"));
                }
        }

        @Nested
        class UpdateCardTests {
                @Test
                void updateCard_ShouldReturnUpdatedCard_WhenExists() throws Exception {
                        CreditCardRequest request = new CreditCardRequest(
                                        1L, "1234567890123456", LocalDate.of(2030, 1, 1), 123, "John Doe");

                        when(creditCardMapper.toModel(any(CreditCardDto.class))).thenReturn(card);
                        when(creditCardService.update(eq(1L), any(CreditCard.class))).thenReturn(card);
                        when(creditCardMapper.toDto(card)).thenReturn(cardDto);

                        mockMvc.perform(put("/api/credit-cards/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.number").value("1234567890123456"));
                }

                @Test
                void updateCard_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        CreditCardRequest request = new CreditCardRequest(
                                        1L, "1234567890123456", LocalDate.of(2030, 1, 1), 123, "John Doe");

                        when(creditCardMapper.toModel(any(CreditCardDto.class))).thenReturn(card);
                        when(creditCardService.update(eq(1L), any(CreditCard.class))).thenReturn(null);

                        mockMvc.perform(put("/api/credit-cards/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class DeleteCardByIdTests {
                @Test
                void deleteCard_ShouldReturnNoContent() throws Exception {
                        doNothing().when(creditCardService).delete(1L);

                        mockMvc.perform(delete("/api/credit-cards/{id}", 1L))
                                        .andExpect(status().isNoContent());
                }
        }
}
