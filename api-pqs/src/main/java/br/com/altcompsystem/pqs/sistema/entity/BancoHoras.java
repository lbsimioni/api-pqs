package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.sistema.dtos.BancoHorasDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class BancoHoras {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 50, nullable = false, unique = false)
    private Float horas;

    @OneToOne
    private Funcionario funcionario;

    @OneToMany
    private List<CartaoPonto> lstCartaoPonto;

    public BancoHoras(){}

    public BancoHoras(BancoHorasDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setHoras(obj.getHoras());
        this.setFuncionario(obj.getFuncionario());
        this.setLstCartaoPonto(obj.getLstCartaoPonto());
    }
}
