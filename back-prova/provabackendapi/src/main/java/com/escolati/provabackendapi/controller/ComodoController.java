package com.escolati.provabackendapi.controller;

import com.escolati.provabackendapi.dto.PersistComodoDTO;
import com.escolati.provabackendapi.model.Comodo;
import com.escolati.provabackendapi.repository.ComodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comodo")
public class ComodoController {

    @Autowired
    private ComodoRepository comodoRepository;

    @PostMapping
    public ResponseEntity<Comodo> create(@RequestBody PersistComodoDTO dto) {
        Comodo comodo = new Comodo();
        comodo.setNome(dto.nome());
        return ResponseEntity.ok(comodoRepository.save(comodo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comodo> update(@PathVariable Long id, @RequestBody PersistComodoDTO dto) {
        Comodo comodo = comodoRepository.findById(id).orElseThrow();
        comodo.setNome(dto.nome());
        return ResponseEntity.ok(comodoRepository.save(comodo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comodo> findById(@PathVariable Long id) {
        Comodo comodo = comodoRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(comodo);
    }

    @GetMapping
    public ResponseEntity<List<Comodo>> findAll() {
        return ResponseEntity.ok(comodoRepository.findAll());
    }
}

