package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.AlunoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.pessoas.entity.Responsavel;
import br.com.altcompsystem.pqs.pessoas.repository.AlunoRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlunoServiceImp implements AlunoService {
    @Autowired
    private AlunoRepository rep;

    @Autowired
    private ResponsavelService service;

    @Override
    public AlunoDTO getAlunoById(Long id) {
        if (id == null || id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Aluno> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Aluno não encontrado");
        }

        return new AlunoDTO(opt.get());

    }

    @Override
    public AlunoDTO getAlunoByNameComplete(String name) {
        if ((name == null) || (name.equals(""))) {
            throw new BusinessException("Nome Inválido");
        }

        Aluno al = this.rep.findByNome(name);

        if (al == null) {
            throw new BusinessException("Aluno não encontrado");
        }

        return new AlunoDTO(al);

    }

    @Override
    public List<AlunoDTO> getAlunoByName(String name) {
        return this.converterLista(this.rep.findByNomeIgnoreCaseContaining(name));
    }

    @Override
    public List<AlunoDTO> getAlunos() {
        return this.converterLista(this.rep.findByEstado(true));
    }

    @Override
    public AlunoDTO saveAluno(Aluno objAluno) {
        this.validar(objAluno);

        Calendar calendar = Calendar.getInstance();

        objAluno.setEstado(true);
        objAluno.setDataInc(calendar);
        objAluno.setResponsavel(new Responsavel(this.service.saveAluno(objAluno)));
        objAluno.setValorMensalidade(0f);

        return this.salvar(objAluno);
    }

    @Override
    public AlunoDTO updateAluno(Aluno objAluno) {
        this.validar(objAluno);

        AlunoDTO al = this.getAlunoById(objAluno.getCodigo());

        objAluno.setDataInc(al.getDataInc());
        objAluno.setEstado(al.getEstado());

        objAluno.setResponsavel(new Responsavel(this.service.updateAluno(objAluno, new Aluno(al))));

        return this.salvar(objAluno);
    }

    @Override
    public AlunoDTO mudarEstadoAluno(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Aluno> opt = this.rep.findById(id);

        if (!opt.isPresent()) {
            throw new BusinessException("Aluno não encontrado");
        }

        Aluno a = opt.get();
        a.setEstado(!a.getEstado());

        if (a.getEstado()){
            a.setResponsavel(new Responsavel(this.service.saveAluno(a)));
        } else {
            a.setResponsavel(new Responsavel(this.service.deleteAluno(a)));
        }

        return this.salvar(a);


    }

    private List<AlunoDTO> converterLista(List<Aluno> lstOriginal){
        List<AlunoDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new AlunoDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Aluno obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhum aluno fornecido");
    }

    private AlunoDTO salvar(Aluno objAluno) {
        try{
            this.rep.save(objAluno);
            return new AlunoDTO(objAluno);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
