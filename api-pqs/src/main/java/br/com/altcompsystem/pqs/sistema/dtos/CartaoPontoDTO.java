package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.CartaoPonto;
import lombok.Data;

import java.util.Calendar;

@Data
public class CartaoPontoDTO {
    private Long codigo;
    private Calendar data;
    private Float horarioInic;
    private Float horarioFim;
    private Float cargaHoraria;

    public CartaoPontoDTO(){

    }

    public CartaoPontoDTO(CartaoPonto obj) {
        this.setCodigo(obj.getCodigo());
        this.setData(obj.getData());
        this.setHorarioInic(obj.getHorarioInic());
        this.setHorarioFim(obj.getHorarioInic());
        this.setCargaHoraria(obj.getCargaHoraria());
    }
}
