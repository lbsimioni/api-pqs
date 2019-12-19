package br.com.altcompsystem.pqs.pessoas.entity;

import br.com.altcompsystem.pqs.pessoas.dtos.AlunoDTO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 70, nullable = false, unique = false)
    @NotEmpty(message = "Nome é um campo obrigatório")
    private String nome;

    @Column(length = 30, nullable = false, unique = false)
    private Float valorMensalidade;

    @Column(length = 30, nullable = false, unique = false)
    private String cargaHoraria;

    @Column(nullable = false, unique = false)
    private Calendar dataInc;

    @Column(nullable = true, unique = false)
    private Calendar dataNasc;

    @Column(nullable = false, unique = false)
    private Boolean estado;

    @OneToOne
    private Responsavel responsavel;

    public Aluno() {
    }

    public Aluno(AlunoDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setNome(obj.getNome());
        this.setValorMensalidade(obj.getValorMensalidade());
        this.setCargaHoraria(obj.getCargaHoraria());
        this.setDataInc(obj.getDataInc());
        this.setDataNasc(obj.getDataNasc());
        this.setEstado(obj.getEstado());
        this.setResponsavel(obj.getResponsavel());
    }

}
