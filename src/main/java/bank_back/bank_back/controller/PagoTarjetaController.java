package bank_back.bank_back.controller;

import bank_back.bank_back.controller.webmodel.request.PagoTarjetaRequest;
import bank_back.bank_back.domain.service.PagoTarjeta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PagoTarjetaController {

    private final PagoTarjeta pagoTarjeta;

    public PagoTarjetaController(PagoTarjeta pagoTarjeta) {
        this.pagoTarjeta = pagoTarjeta;
    }

    @PostMapping("/pagoTarjeta")
    public ResponseEntity<Void> realizarPago(@RequestBody PagoTarjetaRequest request) {
        pagoTarjeta.realizarPago(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
