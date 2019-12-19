package br.com.altcompsystem.pqs.pessoas.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Aluno findByNome(String nome);

    List<Aluno> findByNomeIgnoreCaseContaining(String nome);
    List<Aluno> findByValorMensalidade(Float valorMensalidade);
    List<Aluno> findByCargaHoraria(int cargaHoraria);
    List<Aluno> findByDataInc(Calendar dataInc);
    List<Aluno> findByDataNasc(Calendar dataNasc);
    List<Aluno> findByEstado(Boolean estado);
}
