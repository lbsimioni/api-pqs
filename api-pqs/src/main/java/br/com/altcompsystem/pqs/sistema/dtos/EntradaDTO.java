package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.Entrada;
import br.com.altcompsystem.pqs.sistema.entity.Produto;
import lombok.Data;

import java.util.Calendar;

@Data
public class EntradaDTO {
    private Long codigo;
    private Calendar data;
    private Float valorUnit;
    private Integer quantidade;
    private Float valorFinal;
    private Produto produto;

    public EntradaDTO(){

    }

    public EntradaDTO(Entrada obj) {
        this.setCodigo(obj.getCodigo());
        this.setData(obj.getData());
        this.setProduto(obj.getProduto());
        this.setValorUnit(obj.getValorUnit());
        this.setQuantidade(obj.getQuantidade());
        this.setValorFinal(obj.getValorFinal());
    }
}
