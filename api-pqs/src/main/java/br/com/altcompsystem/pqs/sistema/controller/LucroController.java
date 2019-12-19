package br.com.altcompsystem.pqs.sistema.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import br.com.altcompsystem.pqs.sistema.service.LucroService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lucro")
public class LucroController extends RestExceptionHandler {
    @Autowired
    private LucroService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response<LucroDTO>> getLucroById(@PathVariable Long id) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.getLucroById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByDate/{mes}/{ano}")
    public ResponseEntity<Response<LucroDTO>> getLucroByDate(@PathVariable String mes, @PathVariable String ano) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.getLucroByDate(mes, ano));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<LucroDTO>>> getLucros() {
        Response<List<LucroDTO>> response = new Response<>();
        response.setData(this.service.getLucros());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<LucroDTO>> saveLucro(@RequestBody Lucro lucro) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.saveLucro(lucro));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/extra")
    public ResponseEntity<Response<LucroDTO>> saveLucroExtra(@RequestBody Lucro lucro) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.saveLucroExtra(lucro));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<LucroDTO>> updateLucro(@RequestBody Lucro lucro) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.updateLucro(lucro));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/extra")
    public ResponseEntity<Response<LucroDTO>> updateLucroExtra(@RequestBody Lucro lucro) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.updateLucroExtra(lucro));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLucro(@PathVariable Long id) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.deleteLucro(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/extra/{id}/{codigoExtra}")
    public ResponseEntity<Response<LucroDTO>> deleteDespesaExtra(@PathVariable Long id, @PathVariable Long codigoExtra) {
        Response<LucroDTO> response = new Response<>();
        response.setData(this.service.deleteLucroExtra(id, codigoExtra));
        return ResponseEntity.ok(response);
    }

}
