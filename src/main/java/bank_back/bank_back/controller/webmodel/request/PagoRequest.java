package bank_back.bank_back.controller.webmodel.request;

import java.math.BigDecimal;

public record PagoRequest(
        BigDecimal importe,
        String concepto) {
}
