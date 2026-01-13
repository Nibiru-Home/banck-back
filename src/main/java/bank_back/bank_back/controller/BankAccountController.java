package bank_back.bank_back.controller;

import bank_back.bank_back.controller.mapper.BankAccountMapper;
import bank_back.bank_back.controller.webmodel.request.BankAccountRequest;
import bank_back.bank_back.controller.webmodel.response.BankAccountResponse;
import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final bank_back.bank_back.domain.mapper.BankAccountMapper bankAccountDomainMapper;

    public BankAccountController(BankAccountService bankAccountService,
            bank_back.bank_back.domain.mapper.BankAccountMapper bankAccountDomainMapper) {
        this.bankAccountService = bankAccountService;
        this.bankAccountDomainMapper = bankAccountDomainMapper;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> findAll() {
        List<BankAccount> bankAccounts = bankAccountService.findAll();
        List<BankAccountResponse> responses = bankAccounts.stream()
                .map(bankAccountDomainMapper::toDto)
                .map(BankAccountMapper.getInstance()::bankAccountDtoToBankAccountResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> findById(@PathVariable Long id) {
        BankAccount bankAccount = bankAccountService.findById(id);
        if (bankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        BankAccountDto dto = bankAccountDomainMapper.toDto(bankAccount);
        return ResponseEntity.ok(BankAccountMapper.getInstance().bankAccountDtoToBankAccountResponse(dto));
    }

    @PostMapping
    public ResponseEntity<BankAccountResponse> create(@RequestBody BankAccountRequest request) {
        BankAccountDto dto = BankAccountMapper.getInstance().bankAccountRequestToBankAccountDto(request);
        BankAccount bankAccount = bankAccountDomainMapper.toModel(dto);
        BankAccount created = bankAccountService.create(bankAccount);
        BankAccountDto createdDto = bankAccountDomainMapper.toDto(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BankAccountMapper.getInstance().bankAccountDtoToBankAccountResponse(createdDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankAccountResponse> update(@PathVariable Long id, @RequestBody BankAccountRequest request) {
        BankAccountDto dto = BankAccountMapper.getInstance().bankAccountRequestToBankAccountDto(request);
        BankAccount bankAccount = bankAccountDomainMapper.toModel(dto);
        BankAccount updated = bankAccountService.update(id, bankAccount);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        BankAccountDto updatedDto = bankAccountDomainMapper.toDto(updated);
        return ResponseEntity.ok(BankAccountMapper.getInstance().bankAccountDtoToBankAccountResponse(updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bankAccountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
