package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.pessoas.dtos.EnderecoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Endereco;

public interface EnderecoService  {
    EnderecoDTO getEnderecoById(Long id);

    EnderecoDTO saveEndereco(Endereco endereco);

    EnderecoDTO updateEndereco(Endereco endereco);

}
