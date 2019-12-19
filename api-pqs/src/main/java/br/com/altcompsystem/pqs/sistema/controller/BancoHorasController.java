package br.com.altcompsystem.pqs.sistema.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.sistema.dtos.BancoHorasDTO;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import br.com.altcompsystem.pqs.sistema.service.BancoHorasService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banco")
public class BancoHorasController extends RestExceptionHandler {
    @Autowired
    private BancoHorasService service;

    @GetMapping("/{id}")
    public ResponseEntity<Response<BancoHorasDTO>> getBancoHorasById(@PathVariable Long id) {
        Response<BancoHorasDTO> response = new Response<>();
        response.setData(this.service.getBancoHorasById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<BancoHorasDTO>>> getBancosHoras() {
        Response<List<BancoHorasDTO>> response = new Response<>();
        response.setData(this.service.getBancosHoras());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<BancoHorasDTO>> saveCartaoPonto(@RequestBody BancoHoras bancoHoras) {
        Response<BancoHorasDTO> response = new Response<>();
        response.setData(this.service.saveCartaoPonto(bancoHoras));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<BancoHorasDTO>> updateBancoHoras(@RequestBody BancoHoras bancoHoras) {
        Response<BancoHorasDTO> response = new Response<>();
        response.setData(this.service.updateBancoHoras(bancoHoras));
        return ResponseEntity.ok(response);
    }


}
