package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.Extra;
import lombok.Data;


@Data
public class ExtraDTO {
    private Long codigo;
    private String area;
    private Float valor;

    public ExtraDTO(){}

    public ExtraDTO(Extra obj){
        this.setCodigo(obj.getCodigo());
        this.setArea(obj.getArea());
        this.setValor(obj.getValor());
    }
}
