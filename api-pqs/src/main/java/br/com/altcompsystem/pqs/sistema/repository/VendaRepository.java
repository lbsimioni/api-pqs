package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
