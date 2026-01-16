package bank_back.bank_back.domain.service;

import bank_back.bank_back.controller.webmodel.request.PagoTarjetaRequest;

public interface PagoTarjeta {
    void realizarPago(PagoTarjetaRequest request);
}
