package bank_back.bank_back.controller;

import bank_back.bank_back.controller.webmodel.request.BankAccountRequest;
import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.service.BankAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import bank_back.bank_back.domain.mapper.BankAccountMapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankAccountController.class)
class BankAccountControllerTest {

        @MockitoBean
        private BankAccountService bankAccountService;

        @MockitoBean
        private BankAccountMapper bankAccountMapper;

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        private BankAccount account;
        private BankAccountDto accountDto;

        @BeforeEach
        void setUp() {
                account = new BankAccount();
                account.setId(1L);
                account.setIban("ES1234567890");

                accountDto = new BankAccountDto(1L, new BigDecimal("1000.00"), "ES1234567890", null, null, null);
        }

        @Nested
        class GetAllAccountsTests {
                @Test
                void getAllAccounts_ShouldReturnAccounts() throws Exception {
                        List<BankAccount> accounts = Collections.singletonList(account);
                        when(bankAccountService.findAll()).thenReturn(accounts);
                        when(bankAccountMapper.toDto(account)).thenReturn(accountDto);

                        mockMvc.perform(get("/api/bank-accounts"))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$[0].id").value(1))
                                        .andExpect(jsonPath("$[0].iban").value("ES1234567890"));
                }
        }

        @Nested
        class GetAccountByIdTests {
                @Test
                void getAccountById_ShouldReturnAccount_WhenExists() throws Exception {
                        when(bankAccountService.findById(1L)).thenReturn(account);
                        when(bankAccountMapper.toDto(account)).thenReturn(accountDto);

                        mockMvc.perform(get("/api/bank-accounts/{id}", 1L))
                                        .andExpect(status().isOk())
                                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.iban").value("ES1234567890"));
                }

                @Test
                void getAccountById_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        when(bankAccountService.findById(1L)).thenReturn(null);

                        mockMvc.perform(get("/api/bank-accounts/{id}", 1L))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class CreateAccountTests {
                @Test
                void createAccount_ShouldReturnCreatedAccount() throws Exception {
                        BankAccountRequest request = new BankAccountRequest(
                                        null, new BigDecimal("1000.00"), "ES1234567890", null);

                        when(bankAccountMapper.toModel(any(BankAccountDto.class))).thenReturn(account);
                        when(bankAccountService.create(any(BankAccount.class))).thenReturn(account);
                        when(bankAccountMapper.toDto(account)).thenReturn(accountDto);

                        mockMvc.perform(post("/api/bank-accounts")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isCreated())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.iban").value("ES1234567890"));
                }
        }

        @Nested
        class UpdateAccountTests {
                @Test
                void updateAccount_ShouldReturnUpdatedAccount_WhenExists() throws Exception {
                        BankAccountRequest request = new BankAccountRequest(
                                        1L, new BigDecimal("1000.00"), "ES1234567890", null);

                        when(bankAccountMapper.toModel(any(BankAccountDto.class))).thenReturn(account);
                        when(bankAccountService.update(eq(1L), any(BankAccount.class))).thenReturn(account);
                        when(bankAccountMapper.toDto(account)).thenReturn(accountDto);

                        mockMvc.perform(put("/api/bank-accounts/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isOk())
                                        .andExpect(jsonPath("$.id").value(1))
                                        .andExpect(jsonPath("$.iban").value("ES1234567890"));
                }

                @Test
                void updateAccount_ShouldReturnNotFound_WhenDoesNotExist() throws Exception {
                        BankAccountRequest request = new BankAccountRequest(
                                        1L, new BigDecimal("1000.00"), "ES1234567890", null);

                        when(bankAccountMapper.toModel(any(BankAccountDto.class))).thenReturn(account);
                        when(bankAccountService.update(eq(1L), any(BankAccount.class))).thenReturn(null);

                        mockMvc.perform(put("/api/bank-accounts/{id}", 1L)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(request)))
                                        .andExpect(status().isNotFound());
                }
        }

        @Nested
        class DeleteAccountByIdTests {
                @Test
                void deleteAccount_ShouldReturnNoContent() throws Exception {
                        doNothing().when(bankAccountService).delete(1L);

                        mockMvc.perform(delete("/api/bank-accounts/{id}", 1L))
                                        .andExpect(status().isNoContent());
                }
        }
}
