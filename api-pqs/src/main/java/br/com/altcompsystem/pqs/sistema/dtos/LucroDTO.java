package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.Extra;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import lombok.Data;

import java.util.List;

@Data
public class LucroDTO {
    private Long codigo;
    private String mes;
    private String ano;
    private Float recebimentoMaterial;
    private Float recebimentoMensalidade;
    private Float Total;
    private List<Extra> extraList;
    private Float valorExtra;

    public LucroDTO() {
    }

    public LucroDTO(Lucro obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setRecebimentoMaterial(obj.getRecebimentoMaterial());
        this.setRecebimentoMensalidade(obj.getRecebimentoMensalidade());
        this.setTotal(obj.getTotal());
        this.setExtraList(obj.getExtraList());
        this.setValorExtra(obj.getValorExtra());
    }
}
