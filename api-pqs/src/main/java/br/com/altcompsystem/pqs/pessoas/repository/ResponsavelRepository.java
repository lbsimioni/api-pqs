package br.com.altcompsystem.pqs.pessoas.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
    Responsavel findByRg(String rg);
    Responsavel findByCpf(String cpf);

    List<Responsavel> findByNomeIgnoreCaseContaining(String nome);
    List<Responsavel> findByQtdAluno(int qtdAluno);
    List<Responsavel> findByValorMes(Float valorMes);
    List<Responsavel> findByEstado(Boolean estado);
}
