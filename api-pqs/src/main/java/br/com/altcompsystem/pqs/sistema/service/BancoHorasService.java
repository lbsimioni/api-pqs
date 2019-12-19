package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.sistema.dtos.BancoHorasDTO;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;

import java.util.List;


public interface BancoHorasService {
    BancoHorasDTO getBancoHorasById(Long id);

    BancoHorasDTO getBancoHorasByFunc(Funcionario obj);

    List<BancoHorasDTO> getBancosHoras();

    BancoHorasDTO saveBancoHoras(Funcionario func);

    BancoHorasDTO saveCartaoPonto(BancoHoras obj);

    BancoHorasDTO updateBancoHoras(BancoHoras obj);
}
