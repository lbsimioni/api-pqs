package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.ExtraDTO;
import br.com.altcompsystem.pqs.sistema.entity.Extra;
import br.com.altcompsystem.pqs.sistema.repository.ExtraRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtraServiceImp implements ExtraService {
    @Autowired
    private ExtraRepository rep;

    @Override
    public ExtraDTO getExtraById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Extra> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Extras não encontrados");
        }

        return new ExtraDTO(opt.get());

    }

    public List<ExtraDTO> getExtras() {
        return this.converterLista(this.rep.findAll());
    }

    public ExtraDTO saveExtra(Extra obj) {
        this.validar(obj);

        return this.salvar(obj);
    }

    public ExtraDTO updateExtra(Extra obj) {
        this.validar(obj);

        return this.salvar(obj);

    }

    public ExtraDTO deleteExtra(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Extra> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Extra não encontrado");
        }

        this.rep.deleteById(id);
        return new ExtraDTO(opt.get());
    }

    private void validar(Extra obj){
        if ((obj == null) || (obj.equals(""))){
            throw new BusinessException("Extra não fornecida");
        }
    }

    private List<ExtraDTO> converterLista(List<Extra> lstOriginal){
        List<ExtraDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new ExtraDTO(objEntity)));

        return lstConversao;
    }

    private ExtraDTO salvar(Extra obj) {
        try {
            this.rep.save(obj);
            return new ExtraDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
