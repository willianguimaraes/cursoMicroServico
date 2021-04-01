package br.com.willian.mspayroll.controller;

import br.com.willian.mspayroll.model.Payment;
import br.com.willian.mspayroll.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Método para buscar um cliente no ms-Worker e calcular o pagamento desse cliente,
     * a chamada para o endPoint no ms-worker e realizada através do feing client na camada de service,
     * caso retorne um erro, o Hystrix entra em ação e aciona o outro endpoit para tratamento dos dados.
     *
     * @param id Identificador do "Worker".
     * @param days Dias que o "Worker" trabalhou.
     * @return {@link ResponseEntity<Payment>} Retorna o pagamento do "Worker".
     */
    @GetMapping(value = "/{workerId}/days/{days}")
    public ResponseEntity<Payment> getPayment(@PathVariable(value = "workerId") Long id,
                                              @PathVariable(value = "days") Integer days) {
        return ResponseEntity.ok().body(paymentService.getPayment(id, days));
    }
}
