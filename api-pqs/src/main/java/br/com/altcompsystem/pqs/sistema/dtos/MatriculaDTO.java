package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.sistema.entity.Matricula;
import lombok.Data;

import java.util.Calendar;

@Data
public class MatriculaDTO {
    private Long codigo;
    private Calendar dataInic;
    private Calendar dataFim;
    private String dataPagamento;
    private Float valor;
    private Float desconto;
    private Float valorFinal;
    private Boolean estado;
    private Aluno aluno;

    public MatriculaDTO() {
    }

    public MatriculaDTO(Matricula obj) {
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
