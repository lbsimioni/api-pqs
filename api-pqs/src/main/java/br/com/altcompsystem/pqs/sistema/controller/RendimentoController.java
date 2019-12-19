package br.com.altcompsystem.pqs.sistema.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.sistema.dtos.RendimentoDTO;
import br.com.altcompsystem.pqs.sistema.entity.Rendimento;
import br.com.altcompsystem.pqs.sistema.service.RendimentoService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/rendimento")
public class RendimentoController extends RestExceptionHandler {
    @Autowired
    private RendimentoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response<RendimentoDTO>> getRendimentoById(@PathVariable Long id) {
        Response<RendimentoDTO> response = new Response<>();
        response.setData(this.service.getRendimentoById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByDate/{mes}/{ano}")
    public ResponseEntity<Response<RendimentoDTO>> getRendimentoByDate(@PathVariable String mes, @PathVariable String ano) {
        Response<RendimentoDTO> response = new Response<>();
        response.setData(this.service.getRendimentoByDate(mes, ano));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<RendimentoDTO>>> getRendimentos() {
        Response<List<RendimentoDTO>> response = new Response<>();
        response.setData(this.service.getRendimentos());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<RendimentoDTO>> saveRendimento(@RequestBody Rendimento rendimento) {
        Response<RendimentoDTO> response = new Response<>();
        response.setData(this.service.saveRendimento(rendimento));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<RendimentoDTO>> updateRendimento(@RequestBody Rendimento rendimento) {
        Response<RendimentoDTO> response = new Response<>();
        response.setData(this.service.updateRendimento(rendimento));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<RendimentoDTO>> deleteRendimento(@PathVariable Long id) {
        Response<RendimentoDTO> response = new Response<>();
        response.setData(this.service.deleteRendimento(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<Response<List<RendimentoDTO>>> getDashboard() {
        Response<List<RendimentoDTO>> response = new Response<>();
        response.setData(this.service.getSemestre());
        return ResponseEntity.ok(response);
    }

}
