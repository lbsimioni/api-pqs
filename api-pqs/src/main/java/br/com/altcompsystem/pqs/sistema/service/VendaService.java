package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.VendaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Venda;

import java.util.List;

public interface VendaService {
    VendaDTO getVendaById(Long id);

    List<VendaDTO> getVendas();

    VendaDTO saveVenda(Venda obj);

}
