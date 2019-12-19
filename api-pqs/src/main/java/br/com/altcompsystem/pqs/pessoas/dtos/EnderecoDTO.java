package br.com.altcompsystem.pqs.pessoas.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import lombok.Data;


@Data
public class EnderecoDTO {
    private Long codigo;
    private int cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String uf;
    private String cidade;
    private String complemento;

    public EnderecoDTO(){

    }

    public EnderecoDTO(Endereco obj){
        this.setCodigo(obj.getCodigo());
        this.setCep(obj.getCep());
        this.setLogradouro(obj.getLogradouro());
        this.setNumero(obj.getNumero());
        this.setBairro(obj.getBairro());
        this.setUf(obj.getUf());
        this.setCidade(obj.getCidade());
        this.setComplemento(obj.getComplemento());
    }



}
