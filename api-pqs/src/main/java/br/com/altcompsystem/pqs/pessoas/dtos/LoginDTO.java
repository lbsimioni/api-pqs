package br.com.altcompsystem.pqs.pessoas.dtos;

import br.com.altcompsystem.pqs.pessoas.entity.Login;
import lombok.Data;

@Data
public class LoginDTO {
    private String usuario;
    private String password;

    public LoginDTO(){

    }

    public LoginDTO(Login obj){
        this.usuario = obj.getUsuario();
        this.password = obj.getPassword();
    }
}
