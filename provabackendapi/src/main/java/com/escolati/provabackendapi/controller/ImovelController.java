package com.escolati.provabackendapi.controller;

import com.escolati.provabackendapi.dto.CreateImovelDTO;
import com.escolati.provabackendapi.dto.ImovelDto;
import com.escolati.provabackendapi.dto.UpdateImovelDTO;
import com.escolati.provabackendapi.model.Comodo;
import com.escolati.provabackendapi.model.Imovel;
import com.escolati.provabackendapi.repository.ComodoRepository;
import com.escolati.provabackendapi.repository.ImovelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/api/imovel")
public class ImovelController {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private ComodoRepository comodoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ImovelDto> create(@RequestBody CreateImovelDTO dto) {
        Comodo comodo = comodoRepository.findByNomeIgnoreCase(dto.comodo())
                .orElse(Comodo.builder().nome(dto.comodo()).build());
        Imovel imovel = new Imovel(dto);
        imovel.addComodo(comodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ImovelDto(imovelRepository.save(imovel)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImovelDto> update(@PathVariable Long id, @RequestBody UpdateImovelDTO dto) {
        Comodo comodo = null;
        if (nonNull(dto.comodo()) && !dto.comodo().isBlank()) {
            comodo = comodoRepository.findByNomeIgnoreCase(dto.comodo())
                    .orElse(Comodo.builder().nome(dto.comodo()).build());
        }
        Imovel imovel = imovelRepository.findById(id).orElseThrow();
        imovel.update(dto);
        if (comodo == null) {
            imovel.removeComodo();
        } else {
            imovel.addComodo(comodo);
        }
        return ResponseEntity.ok(new ImovelDto(imovelRepository.save(imovel)));
    }

    @GetMapping
    public ResponseEntity<List<ImovelDto>> findAll() {
        return ResponseEntity.ok(imovelRepository.findAll().stream().map(ImovelDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImovelDto> findById(@PathVariable Long id) {
        Imovel imovel = imovelRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new ImovelDto(imovel));
    }
}
