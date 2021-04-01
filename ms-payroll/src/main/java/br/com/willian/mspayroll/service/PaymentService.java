package br.com.willian.mspayroll.service;

import br.com.willian.mspayroll.feingclients.WorkerFeingClient;
import br.com.willian.mspayroll.model.Payment;
import br.com.willian.mspayroll.model.Worker;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private WorkerFeingClient workerFeingClient;

    @Autowired
    public PaymentService(WorkerFeingClient workerFeingClient) {
        this.workerFeingClient = workerFeingClient;
    }

    @HystrixCommand(fallbackMethod = "getPaymentAlternative")
    public Payment getPayment(Long workerId, int days) {
        //Chamada pelo Rest Template
//        Worker worker = restTemplate.getForObject(
//                workerHost + workerId,
//                Worker.class);

        Worker worker = workerFeingClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }

    public Payment getPaymentAlternative(Long workerId, int days) {
        //Chamada pelo Rest Template
//        Worker worker = restTemplate.getForObject(
//                workerHost + workerId,
//                Worker.class);

        Worker worker = workerFeingClient.findById(workerId).getBody();
        return new Payment("Sem Usuario", 400.0, days);
    }
}
