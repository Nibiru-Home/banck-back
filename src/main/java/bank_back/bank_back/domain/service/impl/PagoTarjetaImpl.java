package bank_back.bank_back.domain.service.impl;

import bank_back.bank_back.controller.webmodel.request.PagoTarjetaRequest;
import bank_back.bank_back.domain.dto.BankAccountDto;
import bank_back.bank_back.domain.dto.CreditCardDto;
import bank_back.bank_back.domain.exception.BusinessException;
import bank_back.bank_back.domain.model.BankAccount;
import bank_back.bank_back.domain.service.BankAccountService;
import bank_back.bank_back.domain.service.ClientService;
import bank_back.bank_back.domain.service.CreditCardService;
import bank_back.bank_back.domain.service.PagoTarjeta;
import bank_back.bank_back.domain.validation.DtoValidator;
import org.springframework.transaction.annotation.Transactional;

public class PagoTarjetaImpl implements PagoTarjeta {

    private final ClientService clientService;
    private final BankAccountService bankAccountService;
    private final CreditCardService creditCardService;

    public PagoTarjetaImpl(ClientService clientService, BankAccountService bankAccountService,
            CreditCardService creditCardService) {
        this.clientService = clientService;
        this.bankAccountService = bankAccountService;
        this.creditCardService = creditCardService;
    }

    @Override
    @Transactional
    public void realizarPago(PagoTarjetaRequest request) {
        DtoValidator.validate(request);

        clientService.validate(request.autorizacion().login(), request.autorizacion().apiToken());
        CreditCardDto card = creditCardService.validate(request.origen());

        BankAccount originAccount = bankAccountService.findByCreditCardId(card.id());

        if (originAccount == null) {
            throw new BusinessException("Origin account not found for card");
        }

        BankAccountDto originDto = new BankAccountDto(originAccount.getId(), originAccount.getBalance(),
                originAccount.getIban(), null, null, null);

        bankAccountService.retirar(originDto, card, request.pago().importe(), request.pago().concepto());

        BankAccount destinationAccount = bankAccountService.findByIban(request.destino().iban());
        if (destinationAccount == null) {
            throw new BusinessException("Destination account not found: " + request.destino().iban());
        }

        BankAccountDto destinationDto = new BankAccountDto(destinationAccount.getId(), destinationAccount.getBalance(),
                destinationAccount.getIban(), null, null, null);
        bankAccountService.ingresar(destinationDto, null, request.pago().importe(), request.pago().concepto());
    }
}
