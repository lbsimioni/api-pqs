package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BancoHorasRepository extends JpaRepository<BancoHoras, Long> {
    Optional<BancoHoras> findByFuncionario(Funcionario obj);
}
