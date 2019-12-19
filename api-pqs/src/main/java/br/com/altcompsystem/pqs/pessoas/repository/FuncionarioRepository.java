package br.com.altcompsystem.pqs.pessoas.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByRg(String rg);
    Funcionario findByCpf(String cpf);
    Funcionario findByImg(String img);
    Funcionario findByUsername(String username);

    List<Funcionario> findByNomeIgnoreCaseContaining(String nome);
    List<Funcionario> findByDataNasc(Calendar data);
    List<Funcionario> findByDataEntrada(Calendar data);
    List<Funcionario> findByCargaHoraria(String carga);
    List<Funcionario> findByProfissao(String profissao);
    List<Funcionario> findByEstado(Boolean estado);
    List<Funcionario> findBySalario(Float salario);
    List<Funcionario> findByObs(String obs);

    Optional<Funcionario> findByUsernameAndPassword(String username, String password);

}
