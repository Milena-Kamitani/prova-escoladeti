package com.escolati.provabackendapi.repository;

import com.escolati.provabackendapi.model.Comodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComodoRepository extends JpaRepository<Comodo, Long> {

    Optional<Comodo> findByNomeIgnoreCase(String nome);
}

