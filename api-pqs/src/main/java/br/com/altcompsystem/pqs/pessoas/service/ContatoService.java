package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.pessoas.dtos.ContatoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Contato;

public interface ContatoService {
    ContatoDTO getContatoById(Long id);

    ContatoDTO saveContato(Contato contato);

    ContatoDTO updateContato(Contato contato);
}
