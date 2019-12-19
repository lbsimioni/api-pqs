package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.sistema.dtos.MatriculaDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(nullable = true, unique = false)
    private Calendar dataInic;

    @Column(nullable = true, unique = false)
    private Calendar dataFim;

    @Column(nullable = true, unique = false)
    private String dataPagamento;

    @Column(nullable = false, unique = false)
    private Float valor;

    @Column(nullable = true, unique = false)
    private Float desconto;

    @Column(nullable = false, unique = false)
    private Float valorFinal;

    @Column
    private Boolean estado;

    @ManyToOne
    private Aluno aluno;

    public Matricula() {
    }

    public Matricula(MatriculaDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setDataInic(obj.getDataInic());
        this.setDataFim(obj.getDataFim());
        this.setDataPagamento(obj.getDataPagamento());
        this.setValor(obj.getValor());
        this.setDesconto(obj.getDesconto());
        this.setValorFinal(obj.getValorFinal());
        this.setEstado(obj.getEstado());
        this.setAluno(obj.getAluno());
    }

}
