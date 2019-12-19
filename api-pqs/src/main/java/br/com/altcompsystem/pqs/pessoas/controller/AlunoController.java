package br.com.altcompsystem.pqs.pessoas.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.pessoas.dtos.AlunoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.pessoas.service.AlunoService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController extends RestExceptionHandler {
    @Autowired
    private AlunoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response<AlunoDTO>> getAlunoById(@PathVariable Long id) {
        Response<AlunoDTO> response = new Response<>();
        response.setData(this.service.getAlunoById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Response<List<AlunoDTO>>> getAlunoByName(@PathVariable String name) {
        Response<List<AlunoDTO>> response = new Response<>();
        response.setData(this.service.getAlunoByName(name));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByNameComplete/{name}")
    public ResponseEntity<Response<AlunoDTO>> getAlunoByNameCompleto(@PathVariable String name) {
        Response<AlunoDTO> response = new Response<>();
        response.setData(this.service.getAlunoByNameComplete(name));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<AlunoDTO>>> getAlunos() {
        Response<List<AlunoDTO>> response = new Response<>();
        response.setData(this.service.getAlunos());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<AlunoDTO>> saveAluno(@RequestBody Aluno aluno) {
        Response<AlunoDTO> response = new Response<>();
        response.setData(this.service.saveAluno(aluno));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateAluno(@RequestBody Aluno aluno) {
        Response<AlunoDTO> response = new Response<>();
        response.setData(this.service.updateAluno(aluno));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> mudarEstadoAluno(@PathVariable Long id) {
        Response<AlunoDTO> response = new Response<>();
        response.setData(this.service.mudarEstadoAluno(id));
        return ResponseEntity.ok(response);
    }
}
