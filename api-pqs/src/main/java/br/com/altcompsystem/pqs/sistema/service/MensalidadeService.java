package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.MensalidadeDTO;
import br.com.altcompsystem.pqs.sistema.entity.Mensalidade;

import java.util.List;

public interface MensalidadeService {
    MensalidadeDTO getMensalidadeById(Long id);

    List<MensalidadeDTO> getMensalidades();

    MensalidadeDTO saveMensalidade(Mensalidade obj);

    MensalidadeDTO updateMensalidade(Mensalidade obj);

    MensalidadeDTO pagar(Long id);
}
