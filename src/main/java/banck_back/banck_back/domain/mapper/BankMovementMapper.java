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
                model.getId(),
                model.getAmount(),
                model.getMovementType(),
                model.getMovementOrigin(),
                model.getConcept(),
                model.getTimestamp(),
                null,
                null);
    }

    public BankMovement toModel(BankMovementDto dto) {
        if (dto == null) {
            return null;
        }
        BankMovement model = new BankMovement();
        model.setId(dto.id());
        model.setAmount(dto.amount());
        model.setTimestamp(dto.timestamp());
        model.setMovementType(dto.movementType());
        model.setMovementOrigin(dto.movementOrigin());
        model.setConcept(dto.concept());
        return model;
    }
}
