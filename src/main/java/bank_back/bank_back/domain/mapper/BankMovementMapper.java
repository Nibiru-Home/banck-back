package bank_back.bank_back.domain.mapper;

import org.springframework.stereotype.Component;

import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.model.BankMovement;

@Component
public class BankMovementMapper {

    public BankMovementDto toDto(BankMovement model) {
        if (model == null) {
            return null;
        }
        return new BankMovementDto(
<<<<<<< HEAD:src/main/java/banck_back/banck_back/domain/mapper/BankMovementMapper.java
                model.getId(),
                model.getAmount(),
                model.getMovementType(),
                model.getMovementOrigin(),
                model.getConcept(),
                model.getTimestamp(),
                null,
                null);
=======
                null,
                model.getAmount(),
                model.getDate() != null
                        ? java.time.LocalDateTime.ofInstant(model.getDate().toInstant(),
                                java.time.ZoneId.systemDefault())
                        : null,
                model.getType(),
                model.getOrigin(),
                model.getBankAccount() != null ? null : null);
>>>>>>> origin/develop:src/main/java/bank_back/bank_back/domain/mapper/BankMovementMapper.java
    }

    public BankMovement toModel(BankMovementDto dto) {
        if (dto == null) {
            return null;
        }
        BankMovement model = new BankMovement();
<<<<<<< HEAD:src/main/java/banck_back/banck_back/domain/mapper/BankMovementMapper.java
        model.setId(dto.id());
=======

>>>>>>> origin/develop:src/main/java/bank_back/bank_back/domain/mapper/BankMovementMapper.java
        model.setAmount(dto.amount());
        model.setTimestamp(dto.timestamp());
        model.setMovementType(dto.movementType());
        model.setMovementOrigin(dto.movementOrigin());
        model.setConcept(dto.concept());
        return model;
    }
}
