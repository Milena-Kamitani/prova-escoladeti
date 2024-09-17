package com.escolati.provabackendapi.dto;

import com.escolati.provabackendapi.model.Imovel;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

public record ImovelDto(
        Long id,
        String descricao,
        LocalDate dataCompra,
        String endereco,
        String comodo
) {

    public ImovelDto(Imovel imovel) {
        this(imovel.getId(), imovel.getDescricao(), imovel.getDataCompra(), imovel.getEndereco(),
                nonNull(imovel.getComodo()) ? imovel.getComodo().getNome() : null);
    }
}
