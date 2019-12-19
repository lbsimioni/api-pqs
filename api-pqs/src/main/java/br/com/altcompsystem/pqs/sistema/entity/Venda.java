package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import br.com.altcompsystem.pqs.sistema.dtos.VendaDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(nullable = false, unique = false)
    private Calendar data;

    @Column(nullable = false, unique = false)
    private Float valorUnit;

    @Column(nullable = false, unique = false)
    private Integer quantidade;

    @Column(nullable = false, unique = false)
    private Float valorFinal;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Responsavel responsavel;

    public Venda(){

    }

    public Venda(VendaDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setData(obj.getData());
        this.setProduto(obj.getProduto());
        this.setResponsavel(obj.getResponsavel());
        this.setValorUnit(obj.getValorUnit());
        this.setQuantidade(obj.getQuantidade());
        this.setValorFinal(obj.getValorFinal());
    }
}
