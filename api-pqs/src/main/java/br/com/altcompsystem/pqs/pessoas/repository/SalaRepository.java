package br.com.altcompsystem.pqs.pessoas.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaRepository extends JpaRepository<Sala, Long> {
    List<Sala>findByQtdAluno(int qtdAluno);
    List<Sala>findBySerie(String serie);
}
