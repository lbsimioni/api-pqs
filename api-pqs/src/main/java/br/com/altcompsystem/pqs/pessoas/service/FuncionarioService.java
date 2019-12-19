package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.pessoas.dtos.FuncionarioDTO;
import br.com.altcompsystem.pqs.pessoas.dtos.LoginDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;

import java.util.List;

public interface FuncionarioService {
    FuncionarioDTO getFuncionarioById(Long id);

    FuncionarioDTO getFuncionarioByRg(String rg);

    List<FuncionarioDTO> getFuncionarioByName(String name);

    List<FuncionarioDTO> getFuncionarios();

    FuncionarioDTO saveFuncionario(Funcionario obj);

    FuncionarioDTO updateFuncionario(Funcionario obj);

    FuncionarioDTO mudarEstadoFuncionario(Long id);

    FuncionarioDTO autenticar(LoginDTO objLogin);

}
