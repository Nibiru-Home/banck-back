package bank_back.bank_back.controller;

import bank_back.bank_back.controller.mapper.CreditCardMapper;
import bank_back.bank_back.controller.webmodel.request.CreditCardRequest;
import bank_back.bank_back.controller.webmodel.response.CreditCardResponse;
import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;
    private final bank_back.bank_back.domain.mapper.CreditCardMapper creditCardDomainMapper;

    public CreditCardController(CreditCardService creditCardService,
            bank_back.bank_back.domain.mapper.CreditCardMapper creditCardDomainMapper) {
        this.creditCardService = creditCardService;
        this.creditCardDomainMapper = creditCardDomainMapper;
    }

    @GetMapping
    public ResponseEntity<List<CreditCardResponse>> findAll() {
        List<CreditCard> creditCards = creditCardService.findAll();
        List<CreditCardResponse> responses = creditCards.stream()
                .map(creditCardDomainMapper::toDto)
                .map(CreditCardMapper.getInstance()::creditCardDtoToCreditCardResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<CreditCardResponse>> findByClientId(@PathVariable java.util.UUID id) {
        List<CreditCard> creditCards = creditCardService.findByClientId(id);
        List<CreditCardResponse> responses = creditCards.stream()
                .map(creditCardDomainMapper::toDto)
                .map(CreditCardMapper.getInstance()::creditCardDtoToCreditCardResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardResponse> findById(@PathVariable Long id) {
        CreditCard creditCard = creditCardService.findById(id);
        if (creditCard == null) {
            return ResponseEntity.notFound().build();
        }
        CreditCardDto dto = creditCardDomainMapper.toDto(creditCard);
        return ResponseEntity.ok(CreditCardMapper.getInstance().creditCardDtoToCreditCardResponse(dto));
    }

    @PostMapping
    public ResponseEntity<CreditCardResponse> create(@RequestBody CreditCardRequest request) {
        CreditCardDto dto = CreditCardMapper.getInstance().creditCardRequestToCreditCardDto(request);
        CreditCard creditCard = creditCardDomainMapper.toModel(dto);
        CreditCard created = creditCardService.create(creditCard);
        CreditCardDto createdDto = creditCardDomainMapper.toDto(created);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CreditCardMapper.getInstance().creditCardDtoToCreditCardResponse(createdDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardResponse> update(@PathVariable Long id, @RequestBody CreditCardRequest request) {
        CreditCardDto dto = CreditCardMapper.getInstance().creditCardRequestToCreditCardDto(request);
        CreditCard creditCard = creditCardDomainMapper.toModel(dto);
        CreditCard updated = creditCardService.update(id, creditCard);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        CreditCardDto updatedDto = creditCardDomainMapper.toDto(updated);
        return ResponseEntity.ok(CreditCardMapper.getInstance().creditCardDtoToCreditCardResponse(updatedDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        creditCardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
