package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.ContatoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import br.com.altcompsystem.pqs.pessoas.repository.ContatoRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImp implements ContatoService {
    @Autowired
    private ContatoRepository rep;

    @Override
    public ContatoDTO getContatoById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Contato> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Contato não encontrado");
        }

        return new ContatoDTO(opt.get());

    }

    @Override
    public ContatoDTO saveContato(Contato objContato) {
        this.validar(objContato);

        if (this.rep.findByCelular(objContato.getCelular()) != null){
            if(!(this.rep.findByCelular(objContato.getCelular()).getCelular().equals(objContato.getCelular()))){
                throw new BusinessException("Celular já existente!");
            }
        }

        if (this.rep.findByEmail(objContato.getEmail()) != null){
            if (!(this.rep.findByEmail(objContato.getEmail()).getEmail().equals(objContato.getEmail()))){
                throw new BusinessException("E-mail já existente!");
            }
        }

        return this.salvar(objContato);
    }

    public ContatoDTO updateContato(Contato objContato) {
        this.validar(objContato);

        ContatoDTO cont = this.getContatoById(objContato.getCodigo());

        if (!objContato.getCelular().equals(cont.getCelular())){
            if (this.rep.findByCelular(objContato.getCelular()) != null) {
                throw new BusinessException("Celular já existente!");
            }
        }

        if (!objContato.getEmail().equals(cont.getEmail())){
            if (this.rep.findByEmail(objContato.getEmail()) != null){
                throw new BusinessException("E-mail já existente!");
            }
        }

        return this.salvar(objContato);

    }

    private void validar(Contato obj){
        if ((obj == null) || (obj.equals(""))){
            throw new BusinessException("Contato não fornecido");
        }
    }

    private ContatoDTO salvar(Contato objContato) {
        try{
            this.rep.save(objContato);
            return new ContatoDTO(objContato);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }

}
