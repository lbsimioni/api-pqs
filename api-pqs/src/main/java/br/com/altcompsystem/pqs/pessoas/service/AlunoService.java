package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.pessoas.dtos.AlunoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Aluno;

import java.util.List;

public interface AlunoService {
    AlunoDTO getAlunoById(Long id);

    List<AlunoDTO> getAlunoByName(String nome);

    AlunoDTO getAlunoByNameComplete(String nome);

    List<AlunoDTO> getAlunos();

    AlunoDTO saveAluno(Aluno obj);

    AlunoDTO updateAluno(Aluno obj);

    AlunoDTO mudarEstadoAluno(Long id);

}
