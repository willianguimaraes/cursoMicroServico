package br.com.willian.mspayroll.feingclients;

import br.com.willian.mspayroll.model.Worker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Interface componente do feing client,
 * passando o nome da aplicação para ser balanceada pelo ribbon,
 * e o path da classe.
 *
 * @author WillianGuimarães
 * @since 17/03/2021
 */

@Component
@FeignClient(name = "ms-worker",
        path = "/workers")
public interface WorkerFeingClient {

    /**
     * Método para ser utilizado pelo feing, ao ser chamado ele já
     * realiza a chamada no endpoint find by id no servico msWorker.
     *
     * @param id Identificador do worker.
     * @return {@link ResponseEntity<Worker>} Retorna um response entity
     * contendo um "Worker" do id correspondente.
     */
    @GetMapping(value = "/{id}")
    ResponseEntity<Worker> findById(@PathVariable Long id);
}
