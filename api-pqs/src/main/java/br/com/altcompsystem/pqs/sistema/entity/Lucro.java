package br.com.altcompsystem.pqs.sistema.entity;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Lucro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 2, unique = false, nullable = false)
    private String mes;

    @Column(length = 5, unique = false, nullable = false)
    private String ano;

    @Column(length = 200, nullable = true, unique = false)
    private Float recebimentoMaterial;

    @Column(length = 200, nullable = false, unique = false)
    private Float recebimentoMensalidade;

    @OneToMany
    private List<Extra> extraList;

    @Column(length = 50, nullable = true, unique = false)
    private Float valorExtra;

    @Column(length = 20, unique = false, nullable = true)
    private Float Total;

    public Lucro() {
    }

    public Lucro(LucroDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setRecebimentoMaterial(obj.getRecebimentoMaterial());
        this.setRecebimentoMensalidade(obj.getRecebimentoMensalidade());
        this.setTotal(obj.getTotal());
        this.setExtraList(obj.getExtraList());
        this.setValorExtra(obj.getValorExtra());
    }

}
