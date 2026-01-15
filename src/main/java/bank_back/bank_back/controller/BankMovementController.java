package bank_back.bank_back.controller;

import bank_back.bank_back.controller.mapper.BankMovementMapper;
import bank_back.bank_back.controller.webmodel.request.BankMovementRequest;
import bank_back.bank_back.controller.webmodel.response.BankMovementResponse;
import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.model.BankMovement;
import bank_back.bank_back.domain.service.BankMovementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank-movements")
public class BankMovementController {

    private final BankMovementService bankMovementService;
    private final bank_back.bank_back.domain.mapper.BankMovementMapper bankMovementDomainMapper;

    public BankMovementController(BankMovementService bankMovementService,
            bank_back.bank_back.domain.mapper.BankMovementMapper bankMovementDomainMapper) {
        this.bankMovementService = bankMovementService;
        this.bankMovementDomainMapper = bankMovementDomainMapper;
    }

    @GetMapping
    public ResponseEntity<List<BankMovementResponse>> findAll() {
        List<BankMovement> bankMovements = bankMovementService.findAll();
        List<BankMovementResponse> responses = bankMovements.stream()
                .map(bankMovementDomainMapper::toDto)
                .map(BankMovementMapper.getInstance()::bankMovementDtoToBankMovementResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankMovementResponse> findById(@PathVariable Long id) {
        BankMovement bankMovement = bankMovementService.findById(id);
        if (bankMovement == null) {
            return ResponseEntity.notFound().build();
        }
        BankMovementDto dto = bankMovementDomainMapper.toDto(bankMovement);
        return ResponseEntity.ok(BankMovementMapper.getInstance().bankMovementDtoToBankMovementResponse(dto));
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<List<BankMovementResponse>> findByCreditCardId(@PathVariable Long id) {
        List<BankMovement> bankMovements = bankMovementService.findByCreditCardId(id);
        List<BankMovementResponse> responses = bankMovements.stream()
                .map(bankMovementDomainMapper::toDto)
                .map(BankMovementMapper.getInstance()::bankMovementDtoToBankMovementResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping
    public ResponseEntity<BankMovementResponse> create(@RequestBody BankMovementRequest request) {
        BankMovementDto dto = BankMovementMapper.getInstance().bankMovementRequestToBankMovementDto(request);
        BankMovement bankMovement = bankMovementDomainMapper.toModel(dto);
        BankMovement created = bankMovementService.create(bankMovement);
        BankMovementDto createdDto = bankMovementDomainMapper.toDto(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(BankMovementMapper.getInstance().bankMovementDtoToBankMovementResponse(createdDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BankMovementResponse> update(@PathVariable Long id,
            @RequestBody BankMovementRequest request) {
        BankMovementDto dto = BankMovementMapper.getInstance().bankMovementRequestToBankMovementDto(request);
        BankMovement bankMovement = bankMovementDomainMapper.toModel(dto);
        BankMovement updated = bankMovementService.update(id, bankMovement);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        BankMovementDto updatedDto = bankMovementDomainMapper.toDto(updated);
        return ResponseEntity.ok(BankMovementMapper.getInstance().bankMovementDtoToBankMovementResponse(updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bankMovementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
