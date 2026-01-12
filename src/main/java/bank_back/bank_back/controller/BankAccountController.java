package bank_back.bank_back.controller;

import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.controller.mapper.BankAccountMapper;
import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final BankAccountMapper bankAccountMapper;

    public BankAccountController(BankAccountService bankAccountService, BankAccountMapper bankAccountMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountMapper = bankAccountMapper;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountDto>> findAll() {
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        List<BankAccountDto> dtos = bankAccounts.stream()
                .map(bankAccountMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountDto> findById(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankAccountMapper.toDto(bankAccount));
    }

    @PostMapping
    public ResponseEntity<BankAccountDto> create(@RequestBody BankAccountDto dto) {
        BankAccount bankAccount = bankAccountMapper.toModel(dto);
        BankAccount created = bankAccountService.create(bankAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountDto> update(@PathVariable Long id, @RequestBody BankAccountDto dto) {
        BankAccount bankAccount = bankAccountMapper.toModel(dto);
        BankAccount updated = bankAccountService.update(id, bankAccount);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankAccountMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
