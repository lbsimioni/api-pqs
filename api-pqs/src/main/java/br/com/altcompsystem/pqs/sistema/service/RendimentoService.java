package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.DespesaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.dtos.RendimentoDTO;
import br.com.altcompsystem.pqs.sistema.entity.Rendimento;

import java.util.List;

public interface RendimentoService {
    RendimentoDTO getRendimentoById(Long id);

    RendimentoDTO getRendimentoByDate(String mes, String ano);

    List<RendimentoDTO> getRendimentos();

    RendimentoDTO saveRendimento(Rendimento obj);

    RendimentoDTO updateRendimento(Rendimento obj);

    RendimentoDTO deleteRendimento(Long id);

    RendimentoDTO saveDespesa(DespesaDTO obj);

    RendimentoDTO saveLucro(LucroDTO obj);

    List<RendimentoDTO> getSemestre();
}
