package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.EnderecoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import br.com.altcompsystem.pqs.pessoas.repository.EnderecoRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImp implements EnderecoService {
    @Autowired
    private EnderecoRepository rep;

    @OneToOne
    public EnderecoDTO getEnderecoById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Endereco> opt = this.rep.findById(id);

        if (!opt.isPresent()) {
            throw new BusinessException("Endereço não encontrado");
        }

        return new EnderecoDTO(opt.get());
    }

    public EnderecoDTO saveEndereco(Endereco objEndereco){
        this.validar(objEndereco);

        return this.salvar(objEndereco);

    }

    public EnderecoDTO updateEndereco(Endereco objEndereco) {
        this.validar(objEndereco);

        return this.salvar(objEndereco);

    }

    private void validar(Endereco obj){
        if ((obj == null) || (obj.equals(""))){
            throw new BusinessException("Endereço não fornecido");
        }
    }

    private EnderecoDTO salvar(Endereco objEndereco) {
        try{
            this.rep.save(objEndereco);
            return new EnderecoDTO(objEndereco);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }


}
