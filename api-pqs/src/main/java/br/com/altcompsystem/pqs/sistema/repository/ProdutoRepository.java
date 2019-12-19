package br.com.altcompsystem.pqs.sistema.repository;

import br.com.altcompsystem.pqs.sistema.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeIgnoreCaseContaining(String nome);
}
