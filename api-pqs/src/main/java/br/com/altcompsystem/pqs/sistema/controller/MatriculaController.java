package br.com.altcompsystem.pqs.sistema.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.sistema.dtos.MatriculaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.MensalidadeDTO;
import br.com.altcompsystem.pqs.sistema.entity.Matricula;
import br.com.altcompsystem.pqs.sistema.service.MatriculaService;
import br.com.altcompsystem.pqs.sistema.service.MensalidadeService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matricula")
public class MatriculaController extends RestExceptionHandler {
    @Autowired
    private MatriculaService service;

    @Autowired
    private MensalidadeService mensalidadeService;

    @GetMapping("/{id}")
    public ResponseEntity<Response<MatriculaDTO>> getMatriculaById(@PathVariable Long id) {
        Response<MatriculaDTO> response = new Response<>();
        response.setData(this.service.getMatriculaById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<MatriculaDTO>>> getMatriculas() {
        Response<List<MatriculaDTO>> response = new Response<>();
        response.setData(this.service.getMatriculas());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mensalidade")
    public ResponseEntity<Response<List<MensalidadeDTO>>> getMensalidades() {
        Response<List<MensalidadeDTO>> response = new Response<>();
        response.setData(this.mensalidadeService.getMensalidades());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<MatriculaDTO>> saveMatricula(@RequestBody Matricula matricula) {
        Response<MatriculaDTO> response = new Response<>();
        response.setData(this.service.saveMatricula(matricula));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<MatriculaDTO>> updateMatricula(@RequestBody Matricula matricula) {
        Response<MatriculaDTO> response = new Response<>();
        response.setData(this.service.updateMatricula(matricula));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<MatriculaDTO>> deleteMatriculaById(@PathVariable Long id) {
        Response<MatriculaDTO> response = new Response<>();
        response.setData(this.service.deleteMatricula(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/mensalidade/{id}")
    public ResponseEntity<Response<MensalidadeDTO>> pagar(@PathVariable Long id) {
        Response<MensalidadeDTO> response = new Response<>();
        response.setData(this.mensalidadeService.pagar(id));
        return ResponseEntity.ok(response);
    }
}
