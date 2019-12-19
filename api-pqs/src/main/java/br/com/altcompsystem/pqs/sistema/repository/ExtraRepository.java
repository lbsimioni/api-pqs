package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.Extra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtraRepository extends JpaRepository<Extra, Long> {
    List<Extra> findByAreaIgnoreCaseContaining(String area);
}
