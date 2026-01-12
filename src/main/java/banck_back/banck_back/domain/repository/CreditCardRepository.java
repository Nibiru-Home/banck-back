package banck_back.banck_back.domain.repository;

import banck_back.banck_back.domain.model.CreditCard;
import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {
    List<CreditCard> findAll();
    Optional<CreditCard> findById(Long id);
    CreditCard save(CreditCard creditCard);
    void deleteById(Long id);
}
