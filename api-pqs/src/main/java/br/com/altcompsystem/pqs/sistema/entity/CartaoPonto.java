package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.sistema.dtos.CartaoPontoDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class CartaoPonto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 200, nullable = false, unique = false)
    private Calendar data;

    @Column(length = 50, nullable = false, unique = false)
    private Float horarioInic;

    @Column(length = 50, nullable = false, unique = false)
    private Float horarioFim;

    @Column(nullable = false, unique = false)
    private Float cargaHoraria;

    public CartaoPonto() {
    }

    public CartaoPonto(CartaoPontoDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setData(obj.getData());
        this.setHorarioInic(obj.getHorarioInic());
        this.setHorarioFim(obj.getHorarioInic());
        this.setCargaHoraria(obj.getCargaHoraria());
    }

}
