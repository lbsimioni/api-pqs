package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.CartaoPontoDTO;
import br.com.altcompsystem.pqs.sistema.entity.CartaoPonto;

import java.util.List;

public interface CartaoPontoService {
    CartaoPontoDTO getCartaoPontoById(Long id);

    List<CartaoPontoDTO> getCartoesPontos();

    CartaoPontoDTO saveCartaoPonto(CartaoPonto obj);

    CartaoPontoDTO updateCartaoPonto(CartaoPonto obj);

    CartaoPontoDTO deleteCartaoPonto(Long id);
}
