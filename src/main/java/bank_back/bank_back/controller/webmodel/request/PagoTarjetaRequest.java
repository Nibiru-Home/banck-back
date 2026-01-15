package bank_back.bank_back.controller.webmodel.request;

import bank_back.bank_back.domain.dto.CreditCardDto;

public record PagoTarjetaRequest(
        AutorizacionRequest autorizacion,
        CreditCardDto origen, // Maps to TarjetaCreditoDto in guide
        DatosCuentaRequest destino,
        PagoRequest pago) {

}
