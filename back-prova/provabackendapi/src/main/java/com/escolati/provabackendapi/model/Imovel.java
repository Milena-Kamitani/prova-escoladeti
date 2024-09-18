package com.escolati.provabackendapi.model;

import com.escolati.provabackendapi.dto.CreateImovelDTO;
import com.escolati.provabackendapi.dto.UpdateImovelDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static java.util.Objects.nonNull;

@Table(name = "tb_imovel")
@Entity
@NoArgsConstructor
@Getter
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dt_compra")
    private LocalDate dataCompra;

    @Column(name = "endereco")
    private String endereco;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comodo_id")

    private Comodo comodo;

    public Imovel(CreateImovelDTO createImovelDTO) {
        this.descricao = createImovelDTO.descricao();
        this.dataCompra = createImovelDTO.dataCompra();
        this.endereco = createImovelDTO.endereco();
    }

    public void addComodo(Comodo comodo) {
        if (nonNull(this.comodo) && this.comodo.getNome().equalsIgnoreCase(comodo.getNome())) return;
        this.comodo = comodo;
    }

    public void removeComodo() {
        this.comodo = null;
    }

    public void update(UpdateImovelDTO dto) {
        this.descricao = dto.descricao();
        this.dataCompra = dto.dataCompra();
        this.endereco = dto.endereco();
    }
}
