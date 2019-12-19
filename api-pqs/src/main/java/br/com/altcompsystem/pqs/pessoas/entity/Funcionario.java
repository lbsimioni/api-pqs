package br.com.altcompsystem.pqs.pessoas.entity;

import br.com.altcompsystem.pqs.pessoas.dtos.FuncionarioDTO;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;

@Data
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 200, unique = false)
    @NotEmpty(message = "Nome é um campo obrigatório")
    private String nome;

    @Column
    private Calendar dataNasc;

    @Column
    private Calendar dataEntrada;

    @Column(length = 50, unique = false)
    private String cargaHoraria;

    @Column(length = 200, unique = false)
    private String profissao;

    @Column(nullable = false, unique = false)
    private Boolean estado;

    @Column(length = 20, nullable = true, unique = false)
    private Float salario;

    @Column(length = 12, unique = true)
    private String rg;

    @Column(length = 14, unique = true)
    private String cpf;

    @Column(length = 200, unique = false)
    private String img;

    @Column(length = 255, nullable = true, unique = false)
    private String obs;

    @Column(length = 100, nullable = true, unique = true)
    private String username;

    @Column(length = 255, nullable = true, unique = false)
    @JsonIgnore
    private String password;

    @OneToOne
    private Endereco endereco;

    @ManyToOne
    private Contato contato;


    public Funcionario() {
    }

    public Funcionario(FuncionarioDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setNome(obj.getNome());
        this.setDataNasc(obj.getDataNasc());
        this.setDataEntrada(obj.getDataEntrada());
        this.setCargaHoraria(obj.getCargaHoraria());
        this.setProfissao(obj.getProfissao());
        this.setEstado(obj.getEstado());
        this.setSalario(obj.getSalario());
        this.setRg(obj.getRg());
        this.setCpf(obj.getCpf());
        this.setImg(obj.getImg());
        this.setObs(obj.getObs());
        this.setUsername(obj.getUsername());
        this.setPassword(obj.getPassword());
        this.setEndereco(obj.getEndereco());
        this.setContato(obj.getContato());
    }

}
