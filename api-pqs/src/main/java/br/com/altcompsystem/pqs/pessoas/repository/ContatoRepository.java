package br.com.altcompsystem.pqs.pessoas.repository;

import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Contato findByEmail(String email);
    Contato findByCelular(String celular);

    List<Contato> findByTelefone(String telefone);
}
