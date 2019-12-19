package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.sistema.dtos.ProdutoDTO;
import br.com.altcompsystem.pqs.sistema.entity.Produto;

import java.util.List;

public interface ProdutoService {
    ProdutoDTO getProdutoById(Long id);

    List<ProdutoDTO> getProdutoByName(String nome);

    List<ProdutoDTO> getProdutos();

    ProdutoDTO saveProduto(Produto obj);

    ProdutoDTO updateProduto(Produto obj);

    ProdutoDTO deleteProduto(Long id);
}
