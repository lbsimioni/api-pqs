package br.com.altcompsystem.pqs.pessoas.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco>findByLogradouro(String logradouro);
    List<Endereco>findByNumero(String numero);
    List<Endereco>findByBairro(String bairro);
    List<Endereco>findByUf(String uf);
    List<Endereco>findByCidade(String cidade);

}
