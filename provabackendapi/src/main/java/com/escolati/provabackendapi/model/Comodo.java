package com.escolati.provabackendapi.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "tb_comodo")
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Comodo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome")
    private String nome;

}
