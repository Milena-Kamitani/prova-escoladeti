package com.escolati.provabackendapi.repository;

import com.escolati.provabackendapi.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
}
