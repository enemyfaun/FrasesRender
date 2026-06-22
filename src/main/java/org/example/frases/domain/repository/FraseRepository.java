package org.example.frases.domain.repository;

import org.example.frases.domain.entities.Frase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraseRepository extends JpaRepository<Frase, Long> {

}
