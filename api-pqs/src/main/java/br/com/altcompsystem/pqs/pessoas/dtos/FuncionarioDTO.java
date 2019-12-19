package br.com.altcompsystem.pqs.pessoas.dtos;

import java.util.Calendar;

import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import lombok.Data;

@Data
public class FuncionarioDTO {
    private Long codigo;
    private String nome;
    private Calendar dataNasc;
    private Calendar dataEntrada;
    private String cargaHoraria;
    private String profissao;
    private Boolean estado;
    private Float salario;
    private String rg;
    private String cpf;
    private String img;
    private String obs;
    private String username;
    private String password;
    private Endereco endereco;
    private Contato contato;

    public FuncionarioDTO(FuncionarioDTO objEntity) {
    }

    public FuncionarioDTO(Funcionario obj) {
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
