package br.com.altcompsystem.pqs.pessoas.entity;

import br.com.altcompsystem.pqs.pessoas.dtos.LoginDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @Column(length = 50, nullable = false, unique = true)
    private String usuario;

    @Column(length = 50, nullable = false, unique = true)
    private String password;

    @Column(nullable = false, unique = false)
    private Boolean admin;

    @Column(length = 50, nullable = true, unique = false)
    private String token;

    public Login() {
    }

    public Login(LoginDTO login) {
        this.setUsuario(login.getUsuario());
        this.setPassword(login.getPassword());
    }
}
