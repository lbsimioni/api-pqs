package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.pessoas.dtos.ResponsavelDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;

import java.util.List;

public interface ResponsavelService {
    ResponsavelDTO getResponsavelById(Long id);

    List<ResponsavelDTO> getResponsavelByName(String nome);

    ResponsavelDTO getResponsavelByRg(String rg);

    List<ResponsavelDTO> getResponsaveis();

    ResponsavelDTO saveResponsavel(Responsavel obj);

    ResponsavelDTO updateResponsavel(Responsavel obj);

    ResponsavelDTO mudarEstadoResponsavel(Long id);

    ResponsavelDTO saveAluno(Aluno obj);

    ResponsavelDTO updateAluno(Aluno objNovo, Aluno obj);

    ResponsavelDTO deleteAluno(Aluno obj);
}
