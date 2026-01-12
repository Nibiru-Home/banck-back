package bank_back.bank_back.controller;

import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.controller.mapper.BankMovementMapper;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.service.BankMovementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bank-movements")
public class BankMovementController {

    private final BankMovementService bankMovementService;
    private final BankMovementMapper bankMovementMapper;

    public BankMovementController(BankMovementService bankMovementService, BankMovementMapper bankMovementMapper) {
        this.bankMovementService = bankMovementService;
        this.bankMovementMapper = bankMovementMapper;
    }

    @GetMapping
    public ResponseEntity<List<BankMovementDto>> findAll() {
        List<BankMovement> bankMovements = bankMovementService.findAll();
        List<BankMovementDto> dtos = bankMovements.stream()
                .map(bankMovementMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankMovementDto> findById(@PathVariable Long id) {
        BankMovement bankMovement = bankMovementService.findById(id);
        if (bankMovement == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankMovementMapper.toDto(bankMovement));
    }

    @PostMapping
    public ResponseEntity<BankMovementDto> create(@RequestBody BankMovementDto dto) {
        BankMovement bankMovement = bankMovementMapper.toModel(dto);
        BankMovement created = bankMovementService.create(bankMovement);
        return ResponseEntity.status(HttpStatus.CREATED).body(bankMovementMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankMovementDto> update(@PathVariable Long id, @RequestBody BankMovementDto dto) {
        BankMovement bankMovement = bankMovementMapper.toModel(dto);
        BankMovement updated = bankMovementService.update(id, bankMovement);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bankMovementMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bankMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
