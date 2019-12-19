package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.sistema.dtos.DespesaDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 2, unique = false, nullable = false)
    private String mes;

    @Column(length = 5, unique = false, nullable = false)
    private String ano;

    @Column(length = 100, unique = false, nullable = false)
    private Float contAgua;

    @Column(length = 100, unique = false, nullable = false)
    private Float contEnergia;

    @Column(length = 100, unique = false, nullable = true)
    private Float contInternet;

    @Column(length = 100, unique = false, nullable = true)
    private Float material;

    @Column(length = 100, unique = false, nullable = false)
    private Float pagamentoFunc;

    @Column(length = 100, unique = false, nullable = true)
    private Float compra;

    @OneToMany
    private List<Extra> extraList;

    @Column(length = 50, nullable = true, unique = false)
    private Float valorExtra;

    @Column(length = 20, unique = false, nullable = true)
    private Float total;

    public Despesa() {
    }

    public Despesa(DespesaDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setContAgua(obj.getContAgua());
        this.setContEnergia(obj.getContEnergia());
        this.setContInternet(obj.getContInternet());
        this.setMaterial(obj.getMaterial());
        this.setPagamentoFunc(obj.getPagamentoFunc());
        this.setCompra(obj.getCompra());
        this.setExtraList(obj.getExtraList());
        this.setValorExtra(obj.getValorExtra());
        this.setTotal(obj.getTotal());
    }

}
