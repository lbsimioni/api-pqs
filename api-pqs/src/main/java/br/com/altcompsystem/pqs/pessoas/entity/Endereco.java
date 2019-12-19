package br.com.altcompsystem.pqs.pessoas.entity;

import br.com.altcompsystem.pqs.pessoas.dtos.EnderecoDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 10, nullable = false, unique = false)
    private int cep;

    @Column(length = 200, nullable = false, unique = false)
    private String logradouro;

    @Column(length = 10, nullable = false, unique = false)
    private String numero;

    @Column(length = 200, nullable = false, unique = false)
    private String bairro;

    @Column(length = 2, nullable = false, unique = false)
    private String uf;

    @Column(length = 200, nullable = false, unique = false)
    private String cidade;

    @Column(length = 200, nullable = true, unique = false)
    private String complemento;

    public Endereco() {
    }

    public Endereco(EnderecoDTO obj) {
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
