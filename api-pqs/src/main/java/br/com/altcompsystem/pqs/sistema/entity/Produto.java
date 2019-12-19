package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.sistema.dtos.ProdutoDTO;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column( length = 200, nullable = false, unique = false)
    private String nome;

    @Column ( length = 50, nullable = false, unique = false)
    private Integer estoque;

    @Column ( length = 50, nullable = false, unique = false)
    private Integer estoqueMinimo;

    @Column ( length = 50, nullable = false, unique = false)
    private Float custo;

    @Column ( length = 50, nullable = false, unique = false)
    private Float valorVenda;

    public Produto (){}

    public Produto (ProdutoDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setNome(obj.getNome());
        this.setEstoque(obj.getEstoque());
        this.setEstoqueMinimo(obj.getEstoqueMinimo());
        this.setCusto(obj.getCusto());
        this.setValorVenda(obj.getValorVenda());
    }
}
