package br.com.altcompsystem.pqs.sistema.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import br.com.altcompsystem.pqs.sistema.entity.CartaoPonto;
import lombok.Data;

import java.util.List;

@Data
public class BancoHorasDTO {
    private Long codigo;
    private Float horas;
    private Funcionario funcionario;
    private List<CartaoPonto> lstCartaoPonto;

    public BancoHorasDTO(){}

    public BancoHorasDTO(BancoHoras obj) {
        this.setCodigo(obj.getCodigo());
        this.setHoras(obj.getHoras());
        this.setFuncionario(obj.getFuncionario());
        this.setLstCartaoPonto(obj.getLstCartaoPonto());
    }
}
