package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.CartaoPonto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartaoPontoRepository extends JpaRepository<CartaoPonto, Long> {
    List<CartaoPonto> findByData(String data);
    List<CartaoPonto> findByHorarioInic(Float hora);
    List<CartaoPonto> findByHorarioFim(Float hora);
}
