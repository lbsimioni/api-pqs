package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.Produto;
import lombok.Data;

@Data
public class ProdutoDTO {
    private Long codigo;
    private String nome;
    private Integer estoque;
    private Integer estoqueMinimo;
    private Float custo;
    private Float valorVenda;

    public ProdutoDTO (){}

    public ProdutoDTO (Produto obj) {
        this.setCodigo(obj.getCodigo());
        this.setNome(obj.getNome());
        this.setEstoque(obj.getEstoque());
        this.setEstoqueMinimo(obj.getEstoqueMinimo());
        this.setCusto(obj.getCusto());
        this.setValorVenda(obj.getValorVenda());
    }
}
