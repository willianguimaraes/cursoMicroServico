package br.com.willian.msworker.service;

import br.com.willian.msworker.model.Worker;
import br.com.willian.msworker.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private WorkerRepository repository;

    @Autowired
    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    public List<Worker> findAll() {
        return repository.findAll();
    }

    public Worker findById(Long id) {
        return repository.findById(id)
                .orElseGet(Worker::new);
    }
}
