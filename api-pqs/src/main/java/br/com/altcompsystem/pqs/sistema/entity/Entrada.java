package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.sistema.dtos.EntradaDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class Entrada {
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

    public Entrada(){

    }

    public Entrada(EntradaDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setData(obj.getData());
        this.setProduto(obj.getProduto());
        this.setValorUnit(obj.getValorUnit());
        this.setQuantidade(obj.getQuantidade());
        this.setValorFinal(obj.getValorFinal());
    }
}
