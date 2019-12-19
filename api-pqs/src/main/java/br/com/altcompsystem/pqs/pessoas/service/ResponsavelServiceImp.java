package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.ResponsavelDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import br.com.altcompsystem.pqs.pessoas.repository.ResponsavelRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ResponsavelServiceImp implements ResponsavelService {
    @Autowired
    private ResponsavelRepository rep;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ContatoService contatoService;

    @Override
    public ResponsavelDTO getResponsavelById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Responsavel> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Responsável não encontrado");
        }
        return new ResponsavelDTO(opt.get());

    }

    @Override
    public List<ResponsavelDTO> getResponsavelByName(String name) {
        return this.converterLista(this.rep.findByNomeIgnoreCaseContaining(name));
    }

    @Override
    public ResponsavelDTO getResponsavelByRg(String rg) {
        try {
            return new ResponsavelDTO(this.rep.findByRg(rg));
        } catch (Exception ex){
            throw new BusinessException("Nenhum responsável encontrado com este RG");
        }

    }

    @Override
    public List<ResponsavelDTO> getResponsaveis() {
        return this.converterLista(this.rep.findByEstado(true));
    }

    @Override
    public ResponsavelDTO saveResponsavel(Responsavel objResponsavel) {
        this.validar(objResponsavel);
        if(this.rep.findByRg(objResponsavel.getRg()) != null){
            throw new BusinessException("RG já registrado!");
        }
        if (this.rep.findByCpf(objResponsavel.getCpf()) != null){
            throw new BusinessException("CPF já registrado!");
        }

        Calendar calendar = Calendar.getInstance();

        objResponsavel.setDataInic(calendar);
        objResponsavel.setQtdAluno(0);
        objResponsavel.setValorMes(0f);
        objResponsavel.setEstado(true);
        objResponsavel.setEndereco(new Endereco(this.enderecoService.saveEndereco(objResponsavel.getEndereco())));
        objResponsavel.setContato(new Contato(this.contatoService.saveContato(objResponsavel.getContato())));
        return this.salvar(objResponsavel);
    }

    @Override
    public ResponsavelDTO updateResponsavel(Responsavel objResponsavel) {
        this.validar(objResponsavel);

        ResponsavelDTO resp = this.getResponsavelById(objResponsavel.getCodigo());

        if (!objResponsavel.getRg().equals(resp.getRg())){
            if (this.rep.findByRg(objResponsavel.getRg()) != null){
                throw new BusinessException("RG já cadastrado");
            }
        }

        if (!objResponsavel.getCpf().equals(resp.getCpf())){
            if (this.rep.findByCpf(objResponsavel.getCpf()) != null){
                throw new BusinessException("CPF já cadastrado");
            }
        }

        objResponsavel.setValorMes(resp.getValorMes());
        objResponsavel.setQtdAluno(resp.getQtdAluno());
        objResponsavel.setEstado(resp.getEstado());
        objResponsavel.setDataInic(resp.getDataInic());

        objResponsavel.setEndereco(new Endereco(this.enderecoService.updateEndereco(objResponsavel.getEndereco())));
        objResponsavel.setContato(new Contato(this.contatoService.updateContato(objResponsavel.getContato())));


        return this.update(objResponsavel);
    }

    @Override
    public ResponsavelDTO mudarEstadoResponsavel(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Responsavel> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Endereço do responsável não encontrado");
        }

        Responsavel r = opt.get();
        r.setEstado(!r.getEstado());
        try {
            this.rep.save(r);
            return new ResponsavelDTO(r);
        } catch (DataAccessException ex){
            throw new BusinessException("Erro ao salvar no banco de dados");
        }


    }

    @Override
    public ResponsavelDTO saveAluno(Aluno objAluno){
        Responsavel objResponsavel = objAluno.getResponsavel();
        objResponsavel = new Responsavel(this.getResponsavelById(objResponsavel.getCodigo()));

        objResponsavel.setQtdAluno(objResponsavel.getQtdAluno() + 1);
        objResponsavel.setEstado(true);
        return this.update(objResponsavel);
    }

    @Override
    public ResponsavelDTO updateAluno(Aluno objAlunoNovo, Aluno objAluno){
        ResponsavelDTO objReset = this.getResponsavelById(objAluno.getResponsavel().getCodigo());
        objReset.setValorMes(objReset.getValorMes() - objAluno.getValorMensalidade());
        objReset.setQtdAluno(objReset.getQtdAluno() - 1);
        objReset = this.update(new Responsavel(objReset));

        if (objAluno.getResponsavel().getCodigo() == objAlunoNovo.getResponsavel().getCodigo()) {
            objAluno.setResponsavel(new Responsavel(objReset));
        }

        // Inserindo novo
        return this.saveAluno(objAlunoNovo);

    }

    @Override
    public ResponsavelDTO deleteAluno(Aluno obj){
        ResponsavelDTO resp = this.getResponsavelById(obj.getResponsavel().getCodigo());

        resp.setValorMes(resp.getValorMes() - obj.getValorMensalidade());
        resp.setQtdAluno(resp.getQtdAluno() - 1);

        return this.update(new Responsavel(resp));
    }

    private List<ResponsavelDTO> converterLista(List<Responsavel> lstOriginal){
        List<ResponsavelDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new ResponsavelDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Responsavel obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhum responsável fornecido");
    }

    private ResponsavelDTO update(Responsavel objResponsavel){
        objResponsavel.setEstado(false);
        if (objResponsavel.getQtdAluno() > 0){
            objResponsavel.setEstado(true);
        }

        return this.salvar(objResponsavel);
    }

    private ResponsavelDTO salvar(Responsavel objResponsavel) {
        try{

            this.rep.save(objResponsavel);
            return new ResponsavelDTO(objResponsavel);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
