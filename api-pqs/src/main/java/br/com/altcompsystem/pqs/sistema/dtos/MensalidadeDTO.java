package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.sistema.entity.Mensalidade;
import lombok.Data;

@Data
public class MensalidadeDTO {
    private Long codigo;
    private String mes;
    private String ano;
    private Float valor;
    private Boolean estado;
    private Aluno aluno;

    public MensalidadeDTO(){

    }

    public MensalidadeDTO(Mensalidade obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setValor(obj.getValor());
        this.setEstado(obj.getEstado());
        this.setAluno(obj.getAluno());
    }
}
