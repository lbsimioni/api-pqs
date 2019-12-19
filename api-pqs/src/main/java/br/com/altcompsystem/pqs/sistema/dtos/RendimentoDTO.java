package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import br.com.altcompsystem.pqs.sistema.entity.Rendimento;
import lombok.Data;

@Data
public class RendimentoDTO {
    private Long codigo;
    private String mes;
    private String ano;
    private Despesa despesa;
    private Float valorDespesa;
    private Lucro lucro;
    private Float valorLucro;
    private Float total;

    public RendimentoDTO() {
    }

    public RendimentoDTO(Rendimento obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setDespesa(obj.getDespesa());
        this.setLucro(obj.getLucro());
        this.setValorDespesa(obj.getValorDespesa());
        this.setValorLucro(obj.getValorLucro());
        this.setTotal(obj.getTotal());
    }
}
