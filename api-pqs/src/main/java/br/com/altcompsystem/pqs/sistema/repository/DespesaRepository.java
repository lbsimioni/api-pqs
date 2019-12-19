package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    Despesa findByMesAndAno(String mes, String ano);
}
