package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import br.com.altcompsystem.pqs.sistema.entity.Produto;
import br.com.altcompsystem.pqs.sistema.entity.Venda;
import lombok.Data;

import java.util.Calendar;

@Data
public class VendaDTO {
    private Long codigo;
    private Calendar data;
    private Float valorUnit;
    private Integer quantidade;
    private Float valorFinal;
    private Produto produto;
    private Responsavel responsavel;

    public VendaDTO(){

    }

    public VendaDTO(Venda obj) {
        this.setCodigo(obj.getCodigo());
        this.setData(obj.getData());
        this.setProduto(obj.getProduto());
        this.setResponsavel(obj.getResponsavel());
        this.setValorUnit(obj.getValorUnit());
        this.setQuantidade(obj.getQuantidade());
        this.setValorFinal(obj.getValorFinal());
    }
}
