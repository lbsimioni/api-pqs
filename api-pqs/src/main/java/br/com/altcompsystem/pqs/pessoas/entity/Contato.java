package br.com.altcompsystem.pqs.pessoas.entity;

import br.com.altcompsystem.pqs.pessoas.dtos.ContatoDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 25, unique = true, nullable = false)
    private String celular;

    @Column(length = 25, unique = false, nullable = true)
    private String telefone;

    @Column(length = 200, unique = true, nullable = false)
    private String email;

    public Contato() {
    }

    public Contato(ContatoDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setCelular(obj.getCelular());
        this.setTelefone(obj.getTelefone());
        this.setEmail(obj.getEmail());
    }

}