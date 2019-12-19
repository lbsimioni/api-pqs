package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.ExtraDTO;
import br.com.altcompsystem.pqs.sistema.entity.Extra;

import java.util.List;

public interface ExtraService {
    ExtraDTO getExtraById(Long id);

    List<ExtraDTO> getExtras();

    ExtraDTO saveExtra(Extra obj);

    ExtraDTO updateExtra(Extra obj);

    ExtraDTO deleteExtra(Long id);
}
