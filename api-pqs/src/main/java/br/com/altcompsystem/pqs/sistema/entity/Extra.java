package br.com.altcompsystem.pqs.sistema.entity;
import br.com.altcompsystem.pqs.sistema.dtos.ExtraDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 50, nullable = false, unique = false)
    private String area;

    @Column(length = 30, nullable = false, unique = false)
    private Float valor;

    public Extra() {
    }

    public Extra(ExtraDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setArea(obj.getArea());
        this.setValor(obj.getValor());
    }

}
