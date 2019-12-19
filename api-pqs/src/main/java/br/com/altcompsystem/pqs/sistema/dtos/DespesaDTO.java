package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import br.com.altcompsystem.pqs.sistema.entity.Extra;
import lombok.Data;

import java.util.List;

@Data
public class DespesaDTO {
    private Long codigo;
    private String mes;
    private String ano;
    private Float contAgua;
    private Float contEnergia;
    private Float contInternet;
    private Float material;
    private Float pagamentoFunc;
    private Float compra;
    private List<Extra> extraList;
    private Float valorExtra;
    private Float total;

    public DespesaDTO(){

    }

    public DespesaDTO(Despesa obj){
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setContAgua(obj.getContAgua());
        this.setContEnergia(obj.getContEnergia());
        this.setContInternet(obj.getContInternet());
        this.setMaterial(obj.getMaterial());
        this.setPagamentoFunc(obj.getPagamentoFunc());
        this.setCompra(obj.getCompra());
        this.setExtraList(obj.getExtraList());
        this.setValorExtra(obj.getValorExtra());
        this.setTotal(obj.getTotal());
    }
}
