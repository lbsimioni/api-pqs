package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LucroRepository extends JpaRepository<Lucro, Long> {
    Lucro findByMesAndAno(String mes, String ano);
}
