package br.com.altcompsystem.pqs.pessoas.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import lombok.Data;

import java.util.Calendar;

@Data
public class AlunoDTO {
    private Long codigo;
    private String nome;
    private Float valorMensalidade;
    private String cargaHoraria;
    private Calendar dataInc;
    private Calendar dataNasc;
    private Boolean estado;
    private Responsavel responsavel;

    public AlunoDTO(){

    }

    public AlunoDTO(Aluno obj){
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
