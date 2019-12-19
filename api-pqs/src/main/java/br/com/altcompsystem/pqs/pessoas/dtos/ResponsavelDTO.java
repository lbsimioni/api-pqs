package br.com.altcompsystem.pqs.pessoas.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import lombok.Data;

import java.util.Calendar;

@Data
public class ResponsavelDTO {
    private Long codigo;
    private String nome;
    private String rg;
    private String cpf;
    private int qtdAluno;
    private Float valorMes;
    private Boolean estado;
    private Calendar dataInic;
    private Endereco endereco;
    private Contato contato;

    public ResponsavelDTO(){

    }

    public ResponsavelDTO(Responsavel obj){
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
