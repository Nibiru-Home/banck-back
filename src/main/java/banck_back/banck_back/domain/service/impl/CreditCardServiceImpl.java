package banck_back.banck_back.domain.service.impl;

import banck_back.banck_back.domain.model.CreditCard;
import banck_back.banck_back.domain.repository.CreditCardRepository;
import banck_back.banck_back.domain.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
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
}
