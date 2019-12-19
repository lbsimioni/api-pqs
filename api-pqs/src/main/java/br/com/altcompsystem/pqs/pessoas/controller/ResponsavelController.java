package br.com.altcompsystem.pqs.pessoas.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.pessoas.dtos.ResponsavelDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import br.com.altcompsystem.pqs.pessoas.service.ResponsavelService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/responsavel")
@RestController
public class ResponsavelController extends RestExceptionHandler {
    @Autowired
    private ResponsavelService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response<ResponsavelDTO>> getResponsavelById(@PathVariable Long id) {
        Response<ResponsavelDTO> response = new Response<>();
        response.setData(this.service.getResponsavelById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<Response<List<ResponsavelDTO>>> getResponsavelByName(@PathVariable String name) {
        Response<List<ResponsavelDTO>> response = new Response<>();
        response.setData(this.service.getResponsavelByName(name));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByRg/{rg}")
    public ResponseEntity<Response<ResponsavelDTO>> getResponsavelByRg(@PathVariable String rg) {
        Response<ResponsavelDTO> response = new Response<>();
        response.setData(this.service.getResponsavelByRg(rg));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<ResponsavelDTO>>> getResponsaveis() throws Exception {
        Response<List<ResponsavelDTO>> response = new Response<>();
        response.setData(this.service.getResponsaveis());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<ResponsavelDTO>> saveResponsavel(@RequestBody Responsavel responsavel) {
        Response<ResponsavelDTO> response = new Response<>();
        response.setData(this.service.saveResponsavel(responsavel));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<ResponsavelDTO>> updateResponsavel(@RequestBody Responsavel responsavel) {
        Response<ResponsavelDTO> response = new Response<>();
        response.setData(this.service.updateResponsavel(responsavel));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ResponsavelDTO>> mudarEstadoResponsavel(@PathVariable Long id) {
        Response<ResponsavelDTO> response = new Response<>();
        response.setData(this.service.mudarEstadoResponsavel(id));
        return ResponseEntity.ok(response);
    }
}
