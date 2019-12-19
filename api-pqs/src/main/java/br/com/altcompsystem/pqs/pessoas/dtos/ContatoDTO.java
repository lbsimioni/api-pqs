package br.com.altcompsystem.pqs.pessoas.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import lombok.Data;

@Data
public class ContatoDTO {
    private Long codigo;
    private String celular;
    private String telefone;
    private String email;

    public ContatoDTO(){

    }

    public ContatoDTO(Contato obj){
        this.setCodigo(obj.getCodigo());
        this.setCelular(obj.getCelular());
        this.setTelefone(obj.getTelefone());
        this.setEmail(obj.getEmail());
    }

}
