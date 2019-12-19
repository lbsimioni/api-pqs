package br.com.altcompsystem.pqs.pessoas.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 3,nullable = false,unique = false)
    private int qtdAluno;

    @Column(length = 15, nullable = false, unique = false)
    private String serie;

    public Sala() {
    }

    public Sala(Long codigo, @NotEmpty(message = "Quantidade de alunos é um campo obrigatório") int qtdAluno, @NotEmpty(message = "Série é um campo obrigatório") String serie) {
        this.setCodigo(codigo);
        this.setQtdAluno(qtdAluno);
        this.setSerie(serie);
    }

}
