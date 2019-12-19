package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.dtos.VendaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Extra;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;

import java.util.List;

public interface LucroService {
    LucroDTO getLucroById(Long id);

    LucroDTO getLucroByDate(String mes, String ano);

    List<LucroDTO> getLucros();

    LucroDTO saveLucro(Lucro obj);

    LucroDTO saveMaterial(VendaDTO obj);

    LucroDTO updateLucro(Lucro obj);

    LucroDTO deleteLucro(Long id);

    LucroDTO updateByDate(String mes, String ano);

    LucroDTO saveLucroExtra(Lucro obj);

    LucroDTO updateLucroExtra(Lucro obj);

    LucroDTO deleteLucroExtra(Long codigo, Long codigoExtra);
}
