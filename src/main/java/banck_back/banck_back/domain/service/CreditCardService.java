package banck_back.banck_back.domain.service;

import banck_back.banck_back.domain.model.CreditCard;
import java.util.List;

public interface CreditCardService {
    List<CreditCard> findAll();
    CreditCard findById(Long id);
    CreditCard create(CreditCard creditCard);
    CreditCard update(Long id, CreditCard creditCard);
    void delete(Long id);
}
