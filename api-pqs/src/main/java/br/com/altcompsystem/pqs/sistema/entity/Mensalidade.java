package br.com.altcompsystem.pqs.sistema.entity;

import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.sistema.dtos.MensalidadeDTO;
import br.com.altcompsystem.pqs.utils.Mensagens;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Mensalidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 50, nullable = false, unique = false)
    private String mes;

    @Column(length = 50, nullable = false, unique = false)
    private String ano;

    @Column(nullable = false, unique = false)
    private Float valor;

    @Column(nullable = false, unique = false)
    private Boolean estado;

    @ManyToOne
    private Aluno aluno;

    public Mensalidade(){

    }

    public Mensalidade(MensalidadeDTO obj) {
        this.setCodigo(obj.getCodigo());
        this.setMes(obj.getMes());
        this.setAno(obj.getAno());
        this.setValor(obj.getValor());
        this.setEstado(obj.getEstado());
        this.setAluno(obj.getAluno());
    }
}
