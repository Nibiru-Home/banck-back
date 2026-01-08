package banck_back.banck_back.domain.mapper;

import banck_back.banck_back.domain.dto.BankMovementDto;
import banck_back.banck_back.domain.model.BankMovement;
import org.springframework.stereotype.Component;

@Component
public class BankMovementMapper {

    public BankMovementDto toDto(BankMovement model) {
        if (model == null) {
            return null;
        }
        return new BankMovementDto(
                null, // Model has no ID currently
                model.getAmount(),
                model.getDate() != null
                        ? java.time.LocalDateTime.ofInstant(model.getDate().toInstant(),
                                java.time.ZoneId.systemDefault())
                        : null,
                model.getType(),
                model.getOrigin(),
                model.getBankAccount() != null ? null : null // Model BankAccount has no ID yet
        );
    }

    public BankMovement toModel(BankMovementDto dto) {
        if (dto == null) {
            return null;
        }
        BankMovement model = new BankMovement();
        // model.setId(dto.id()); // Model has no ID
        model.setAmount(dto.amount());
        model.setDate(dto.timestamp() != null
                ? java.util.Date.from(dto.timestamp().atZone(java.time.ZoneId.systemDefault()).toInstant())
                : null);
        model.setType(dto.type());
        model.setOrigin(dto.origin());
        return model;
    }
}
