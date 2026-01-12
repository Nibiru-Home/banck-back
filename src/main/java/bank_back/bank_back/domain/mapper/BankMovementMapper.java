package bank_back.bank_back.domain.mapper;

import bank_back.bank_back.domain.dto.BankMovementDto;
import bank_back.bank_back.domain.model.BankMovement;

public class BankMovementMapper {

    private static BankMovementMapper INSTANCE;

    private BankMovementMapper() {
    }

    public static BankMovementMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BankMovementMapper();
        }
        return INSTANCE;
    }

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
        model.setAmount(dto.amount());
        model.setTimestamp(dto.timestamp());
        model.setMovementType(dto.movementType());
        model.setMovementOrigin(dto.movementOrigin());
        model.setConcept(dto.concept());
        return model;
    }
}
