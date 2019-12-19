package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.sistema.entity.Mensalidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MensalidadeRepository extends JpaRepository<Mensalidade, Long> {
    List<Mensalidade> findByEstado(Boolean estado);
    Optional<Mensalidade> findByAlunoAndMesAndAno(Aluno a, String mes, String ano);
}
