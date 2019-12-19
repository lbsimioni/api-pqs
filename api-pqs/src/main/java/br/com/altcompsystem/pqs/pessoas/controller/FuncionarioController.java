package br.com.altcompsystem.pqs.pessoas.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.pessoas.dtos.FuncionarioDTO;
import br.com.altcompsystem.pqs.pessoas.dtos.LoginDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.pessoas.service.FuncionarioService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController extends RestExceptionHandler {
    @Autowired
    private FuncionarioService service;

    @PostMapping("/login")
    public ResponseEntity<Response<FuncionarioDTO>> autenticarUsuario(@RequestBody LoginDTO objLogin){
        Response<FuncionarioDTO> response = new Response<>();
        response.setData(this.service.autenticar(objLogin));

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<FuncionarioDTO>> getFuncionarioById(@PathVariable Long id) {
        Response<FuncionarioDTO> response = new Response<>();
        response.setData(this.service.getFuncionarioById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Response<List<FuncionarioDTO>>> getFuncionarioByName(@PathVariable String name) {
        Response<List<FuncionarioDTO>> response = new Response<>();
        response.setData(this.service.getFuncionarioByName(name));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByRg/{rg}")
    public ResponseEntity<Response<FuncionarioDTO>> getFuncionarioByRg(@PathVariable String rg) {
        Response<FuncionarioDTO> response = new Response<>();
        response.setData(this.service.getFuncionarioByRg(rg));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<FuncionarioDTO>>> getFuncionarios() {
        Response<List<FuncionarioDTO>> response = new Response<>();
        response.setData(this.service.getFuncionarios());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<FuncionarioDTO>> saveFuncionario(@RequestBody Funcionario funcionario) {
        Response<FuncionarioDTO> response = new Response<>();
        response.setData(this.service.saveFuncionario(funcionario));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<FuncionarioDTO>> updateFuncionario(@RequestBody Funcionario funcionario) {
        Response<FuncionarioDTO> response = new Response<>();
        response.setData(this.service.updateFuncionario(funcionario));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<FuncionarioDTO>> alterarEstadoFuncionario(@PathVariable Long id) {
        Response<FuncionarioDTO> response = new Response<>();
        response.setData(this.service.mudarEstadoFuncionario(id));
        return ResponseEntity.ok(response);
    }

}
