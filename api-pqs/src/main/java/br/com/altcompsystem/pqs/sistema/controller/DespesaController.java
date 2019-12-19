package br.com.altcompsystem.pqs.sistema.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.sistema.dtos.DespesaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import br.com.altcompsystem.pqs.sistema.service.DespesaService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/despesa")
public class DespesaController extends RestExceptionHandler {
    @Autowired
    private DespesaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response<DespesaDTO>> getDespesaById(@PathVariable Long id) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.getDespesaById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByDate/{mes}/{ano}")
    public ResponseEntity<Response<DespesaDTO>> getDespesaByDate(@PathVariable String mes, @PathVariable String ano) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.getDespesaByDate(mes, ano));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<DespesaDTO>>> getDespesas() {
        Response<List<DespesaDTO>> response = new Response<>();
        response.setData(this.service.getDespesas());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<DespesaDTO>> saveDespesa(@RequestBody Despesa despesa) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.saveDespesa(despesa));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/extra")
    public ResponseEntity<Response<DespesaDTO>> saveDespesaExtra(@RequestBody Despesa despesa) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.saveDespesaExra(despesa));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<DespesaDTO>> updateDespesa(@RequestBody Despesa despesa) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.updateDespesa(despesa));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/extra")
    public ResponseEntity<Response<DespesaDTO>> updateDespesaExtra(@RequestBody Despesa despesa) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.updateDespesaExtra(despesa));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<DespesaDTO>> deleteDespesa(@PathVariable Long id) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.deleteDespesa(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/extra/{id}/{codigoExtra}")
    public ResponseEntity<Response<DespesaDTO>> deleteDespesaExtra(@PathVariable Long id, @PathVariable Long codigoExtra) {
        Response<DespesaDTO> response = new Response<>();
        response.setData(this.service.deleteDespesaExtra(id, codigoExtra));
        return ResponseEntity.ok(response);
    }
}
