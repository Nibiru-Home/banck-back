package bank_back.bank_back.controller;

import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.controller.mapper.CreditCardMapper;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;
    private final CreditCardMapper creditCardMapper;

    public CreditCardController(CreditCardService creditCardService, CreditCardMapper creditCardMapper) {
        this.creditCardService = creditCardService;
        this.creditCardMapper = creditCardMapper;
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDto>> findAll() {
        List<CreditCard> creditCards = creditCardService.findAll();
        List<CreditCardDto> dtos = creditCards.stream()
                .map(creditCardMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardDto> findById(@PathVariable Long id) {
        CreditCard creditCard = creditCardService.findById(id);
        if (creditCard == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditCardMapper.toDto(creditCard));
    }

    @PostMapping
    public ResponseEntity<CreditCardDto> create(@RequestBody CreditCardDto dto) {
        CreditCard creditCard = creditCardMapper.toModel(dto);
        CreditCard created = creditCardService.create(creditCard);
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardDto> update(@PathVariable Long id, @RequestBody CreditCardDto dto) {
        CreditCard creditCard = creditCardMapper.toModel(dto);
        CreditCard updated = creditCardService.update(id, creditCard);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(creditCardMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        creditCardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
