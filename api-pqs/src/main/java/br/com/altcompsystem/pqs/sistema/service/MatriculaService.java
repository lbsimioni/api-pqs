package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.MatriculaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Matricula;

import java.util.List;

public interface MatriculaService {
    MatriculaDTO getMatriculaById(Long id);

    List<MatriculaDTO> getMatriculas();

    MatriculaDTO saveMatricula(Matricula obj);

    MatriculaDTO updateMatricula(Matricula obj);

    MatriculaDTO deleteMatricula(Long id);
}
