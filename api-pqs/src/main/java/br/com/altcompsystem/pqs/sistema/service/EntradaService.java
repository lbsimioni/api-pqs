package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.EntradaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Entrada;

import java.util.List;

public interface EntradaService {
    EntradaDTO getEntradaById(Long id);

    List<EntradaDTO> getEntradas();

    EntradaDTO saveEntrada(Entrada obj);
}
