package br.com.altcompsystem.pqs.sistema.controller;

import br.com.altcompsystem.pqs.handler.RestExceptionHandler;
import br.com.altcompsystem.pqs.sistema.dtos.EntradaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.ProdutoDTO;
import br.com.altcompsystem.pqs.sistema.dtos.VendaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Entrada;
import br.com.altcompsystem.pqs.sistema.entity.Produto;
import br.com.altcompsystem.pqs.sistema.entity.Venda;
import br.com.altcompsystem.pqs.sistema.service.EntradaService;
import br.com.altcompsystem.pqs.sistema.service.ProdutoService;
import br.com.altcompsystem.pqs.sistema.service.VendaService;
import br.com.altcompsystem.pqs.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController extends RestExceptionHandler {
    @Autowired
    private ProdutoService service;

    @Autowired
    private VendaService vendaService;

    @Autowired
    private EntradaService entradaService;

    @GetMapping("/{id}")
    public ResponseEntity<Response<ProdutoDTO>> getProdutoById(@PathVariable Long id) {
        Response<ProdutoDTO> response = new Response<>();
        response.setData(this.service.getProdutoById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/findByName/{nome}")
    public ResponseEntity<Response<List<ProdutoDTO>>> getProdutoByName(@PathVariable String nome) {
        Response<List<ProdutoDTO>> response = new Response<>();
        response.setData(this.service.getProdutoByName(nome));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<ProdutoDTO>>> getProdutos() {
        Response<List<ProdutoDTO>> response = new Response<>();
        response.setData(this.service.getProdutos());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vendas")
    public ResponseEntity<Response<List<VendaDTO>>> getVendas() {
        Response<List<VendaDTO>> response = new Response<>();
        response.setData(this.vendaService.getVendas());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<Response<VendaDTO>> getVendaById(@PathVariable Long id) {
        Response<VendaDTO> response = new Response<>();
        response.setData(this.vendaService.getVendaById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/entrada")
    public ResponseEntity<Response<List<EntradaDTO>>> getEntradas() {
        Response<List<EntradaDTO>> response = new Response<>();
        response.setData(this.entradaService.getEntradas());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/entrada/{id}")
    public ResponseEntity<Response<EntradaDTO>> getEntradaById(@PathVariable Long id) {
        Response<EntradaDTO> response = new Response<>();
        response.setData(this.entradaService.getEntradaById(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<ProdutoDTO>> saveProduto(@RequestBody Produto produto) {
        Response<ProdutoDTO> response = new Response<>();
        response.setData(this.service.saveProduto(produto));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/vendas")
    public ResponseEntity<Response<VendaDTO>> saveVenda(@RequestBody Venda venda) {
        Response<VendaDTO> response = new Response<>();
        response.setData(this.vendaService.saveVenda(venda));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/entrada")
    public ResponseEntity<Response<EntradaDTO>> saveEntrada(@RequestBody Entrada entrada) {
        Response<EntradaDTO> response = new Response<>();
        response.setData(this.entradaService.saveEntrada(entrada));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Response<ProdutoDTO>> updateProduto(@RequestBody Produto produto) {
        Response<ProdutoDTO> response = new Response<>();
        response.setData(this.service.updateProduto(produto));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ProdutoDTO>> deleteProduto(@PathVariable Long id) {
        Response<ProdutoDTO> response = new Response<>();
        response.setData(this.service.deleteProduto(id));
        return ResponseEntity.ok(response);
    }
}
