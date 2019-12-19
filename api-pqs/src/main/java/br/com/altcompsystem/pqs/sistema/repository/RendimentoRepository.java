package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.Rendimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface RendimentoRepository extends JpaRepository<Rendimento, Long> {
    Rendimento findByMesAndAno(String mes, String ano);
}
