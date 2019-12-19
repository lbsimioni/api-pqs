package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.pessoas.entity.Sala;
import br.com.altcompsystem.pqs.pessoas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    @Autowired
    private SalaRepository rep;

    public ResponseEntity<?> getSalaById(Long id) throws Exception {
        if (id < 0){
            throw new Exception("Código Inválido");
        }

        Optional<Sala> opt = this.rep.findById(id);

        if (opt.isPresent()){
            return new ResponseEntity<>(opt.get(), HttpStatus.OK);
        } else {
            throw new Exception("Sala não encontrada");
        }
    }

    public ResponseEntity<?> getSalas() throws Exception {

        List<Sala> sala = this.rep.findAll();

        if (sala.isEmpty()){
            throw new Exception("Nenhuma sala encontrada");
        } else {
            return new ResponseEntity<>(sala.toString(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> saveSala(Sala objSala) throws Exception{
        if ((objSala == null) || (objSala.equals(""))){
            throw new Exception("Sala não fornecida");
        }

        try{
            this.rep.save(objSala);
            return new ResponseEntity<>(objSala, HttpStatus.OK);
        } catch (DataAccessException ex){
            throw new Exception("Erro ao salvar no banco de dados");
        }
    }

    public ResponseEntity<?> updateSala(Sala objSala) throws Exception{
        if ((objSala == null) || (objSala.equals(""))){
            throw new Exception("Sala não fornecida");
        }

        try{
            this.rep.save(objSala);
            return new ResponseEntity<>(objSala, HttpStatus.OK);
        } catch (DataAccessException ex){
            throw new Exception("Erro ao atualizar sala no banco de dados");
        }
    }

    public ResponseEntity<?> deleteSalaById(Long id) throws Exception {
        if (id < 0) {
            throw new Exception("Código Inválido");
        }

        Optional<Sala> opt = this.rep.findById(id);

        if (opt.isPresent()){
            this.rep.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new Exception("Sala não encontrada");
        }
    }

    public ResponseEntity<?> deleteSala(Sala objSala) throws Exception {
        if ((objSala == null) || (objSala.equals(""))) {
            throw new Exception("Endereço do funcionário não fornecido");
        }

        this.rep.delete(objSala);

        return new ResponseEntity<>(objSala, HttpStatus.OK);
    }
}
