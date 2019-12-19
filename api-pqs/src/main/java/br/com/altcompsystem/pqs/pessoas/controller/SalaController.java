package br.com.altcompsystem.pqs.pessoas.controller;

import br.com.altcompsystem.pqs.pessoas.entity.Sala;
import br.com.altcompsystem.pqs.pessoas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sala")
public class SalaController {
    @Autowired
    private SalaService service;

    @GetMapping("/protected/{id}")
    public ResponseEntity<?> getSalaById(@PathVariable Long id) throws Exception {
        return this.service.getSalaById(id);
    }

    @GetMapping("/protected")
    public ResponseEntity<?> getSalas() throws Exception {
        return this.service.getSalas();

    }

    @PostMapping("/admin")
    public ResponseEntity<?> saveSala(@RequestBody Sala sala) throws Exception {
        return this.service.saveSala(sala);
    }

    @PutMapping("/admin")
    public ResponseEntity<?> updateSala(@RequestBody Sala sala) throws Exception {
        return this.service.updateSala(sala);
    }

    @DeleteMapping("/admin")
    public ResponseEntity<?> deleteSala(@RequestBody Sala sala) throws Exception {
        return this.service.deleteSala(sala);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<?> deleteSalaById(@PathVariable Long id) throws Exception {
        return this.service.deleteSalaById(id);
    }
}
