package br.com.willian.msworker.controller;

import br.com.willian.msworker.model.Worker;
import br.com.willian.msworker.service.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerController {

    private WorkerService service;

    private static Logger logger = LoggerFactory.getLogger(WorkerController.class);

    @Autowired
    public WorkerController(WorkerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping(value = "/configs")
    public ResponseEntity<Void> getConfigs () {
        return ResponseEntity.noContent().build();
    }
}
