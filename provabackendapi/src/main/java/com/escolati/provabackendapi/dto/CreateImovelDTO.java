package com.escolati.provabackendapi.dto;

import java.time.LocalDate;

public record CreateImovelDTO(
        String descricao,
        LocalDate dataCompra,
        String endereco,
        String comodo
) {
}
