package br.com.altcompsystem.pqs.sistema.entity;
import br.com.altcompsystem.pqs.sistema.dtos.RendimentoDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 2, unique = false, nullable = false)
    private String mes;

    @Column(length = 5, unique = false, nullable = false)
    private String ano;

    @OneToOne
    private Despesa despesa;

    @Column(length = 20, unique = false, nullable = true)
    private Float valorDespesa;

    @OneToOne
    private Lucro lucro;

    @Column(length = 20, unique = false, nullable = true)
    private Float valorLucro;

    @Column(length = 20, unique = false, nullable = true)
    private Float total;

    public Rendimento() {
    }

    public Rendimento(RendimentoDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setDespesa(obj.getDespesa());
        this.setLucro(obj.getLucro());
        this.setValorDespesa(obj.getValorDespesa());
        this.setValorLucro(obj.getValorLucro());
        this.setTotal(obj.getTotal());
    }

}
