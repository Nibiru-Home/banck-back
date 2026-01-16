package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.domain.model.CreditCard;
import bank_back.bank_back.domain.repository.CreditCardRepository;
import bank_back.bank_back.domain.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public bank_back.bank_back.domain.dto.CreditCardDto validate(
            bank_back.bank_back.domain.dto.CreditCardDto creditCardDto) {
        CreditCard storedCard = creditCardRepository.findByNumber(creditCardDto.number())
                .orElseThrow(() -> new bank_back.bank_back.domain.exception.BusinessException(
                        "Tarjeta no encontrada: " + creditCardDto.number()));

        if (storedCard.getExpirationDate().isBefore(java.time.LocalDate.now())) {
            throw new bank_back.bank_back.domain.exception.BusinessException("Fecha de caducidad incorrecta");
        }

        if (storedCard.getCvv() != creditCardDto.cvv()) {
            throw new bank_back.bank_back.domain.exception.BusinessException("CVC incorrecto");
        }

        if (!storedCard.getName().equalsIgnoreCase(creditCardDto.name())) {
            throw new bank_back.bank_back.domain.exception.BusinessException("Nombre del titular incorrecto");
        }
        return new bank_back.bank_back.domain.dto.CreditCardDto(storedCard.getId(), storedCard.getNumber(),
                storedCard.getExpirationDate(), storedCard.getCvv(), storedCard.getName());
    }

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public CreditCard findById(Long id) {
        return creditCardRepository.findById(id).orElse(null);
    }

    @Override
    public CreditCard create(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }

    @Override
    public CreditCard update(Long id, CreditCard creditCard) {
        if (creditCardRepository.findById(id).isEmpty()) {
            return null;
        }
        creditCard.setId(id);
        return creditCardRepository.save(creditCard);
    }

    @Override
    public void delete(Long id) {
        creditCardRepository.deleteById(id);
    }

    @Override
    public List<CreditCard> findByClientId(java.util.UUID clientId) {
        return creditCardRepository.findByClientId(clientId);
    }
}
