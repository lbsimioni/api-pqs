package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.DespesaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.EntradaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import br.com.altcompsystem.pqs.sistema.entity.Extra;

import java.util.List;

public interface DespesaService {
    DespesaDTO getDespesaById(Long id);

    DespesaDTO getDespesaByDate(String mes, String ano);

    List<DespesaDTO> getDespesas();

    DespesaDTO saveDespesa(Despesa obj);

    DespesaDTO saveMaterial(EntradaDTO obj);

    DespesaDTO updateDespesa(Despesa obj);

    DespesaDTO deleteDespesa(Long id);

    DespesaDTO saveDespesaExra(Despesa obj);

    DespesaDTO updateDespesaExtra(Despesa obj);

    DespesaDTO deleteDespesaExtra(Long codigo, Long codigoExtra);

}
