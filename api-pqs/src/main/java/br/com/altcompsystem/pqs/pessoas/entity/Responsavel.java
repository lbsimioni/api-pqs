package br.com.altcompsystem.pqs.pessoas.entity;

import br.com.altcompsystem.pqs.pessoas.dtos.ResponsavelDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 120, nullable = false, unique = false)
    private String nome;

    @Column(length = 12, nullable = false, unique = true)
    private String rg;

    @Column(length = 14, nullable = false, unique = true)
    private String cpf;

    @Column(length = 3, nullable = false, unique = false)
    private int qtdAluno;

    @Column(length = 40, nullable = false, unique = false)
    private Float valorMes;

    @Column(nullable = false, unique = false)
    private Boolean estado;

    @Column(nullable = false, unique = false)
    private Calendar dataInic;

    @OneToOne
    private Endereco endereco;

    @ManyToOne
    private Contato contato;

    public Responsavel() {
    }

    public Responsavel(ResponsavelDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setNome(obj.getNome());
        this.setRg(obj.getRg());
        this.setCpf(obj.getCpf());
        this.setQtdAluno(obj.getQtdAluno());
        this.setValorMes(obj.getValorMes());
        this.setEstado(obj.getEstado());
        this.setDataInic(obj.getDataInic());
        this.setEndereco(obj.getEndereco());
        this.setContato(obj.getContato());
    }
}
