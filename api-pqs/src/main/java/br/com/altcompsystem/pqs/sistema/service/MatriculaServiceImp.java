package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.AlunoDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Aluno;
import br.com.altcompsystem.pqs.pessoas.service.AlunoService;
import br.com.altcompsystem.pqs.sistema.dtos.MatriculaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Matricula;
import br.com.altcompsystem.pqs.sistema.entity.Mensalidade;
import br.com.altcompsystem.pqs.sistema.repository.MatriculaRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaServiceImp implements MatriculaService {
    @Autowired
    private MatriculaRepository rep;

    @Autowired
    private AlunoService service;

    @Autowired
    private MensalidadeService mensalidadeService;

    @Override
    public MatriculaDTO getMatriculaById(Long id) {
        if(id < 0) {
            throw new BusinessException("Código inválido");
        }

        Optional<Matricula> opt = rep.findById(id);
        if (!opt.isPresent()){
            throw new BusinessException("Matrícula não encontrada");
        }

        return new MatriculaDTO(opt.get());

    }

    @Override
    public List<MatriculaDTO> getMatriculas() {
        return this.converterLista(this.rep.findByEstado(true));
    }

    @Override
    public MatriculaDTO saveMatricula(Matricula obj) {
        this.validar(obj);
        obj.setEstado(true);
        return this.salvar(obj);
    }

    @Override
    public MatriculaDTO updateMatricula(Matricula obj) {
        this.validar(obj);
        MatriculaDTO matricula = this.getMatriculaById(obj.getCodigo());

        obj.setEstado(matricula.getEstado());
        obj.setAluno(obj.getAluno());
        return this.salvar(obj);
    }

    @Override
    public MatriculaDTO deleteMatricula(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Matricula> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Matrícula não encontrada");
        }

        Matricula matricula = opt.get();
        matricula.setEstado(false);

        return this.salvar(matricula);

    }

    private List<MatriculaDTO> converterLista(List<Matricula> lstOriginal){
        List<MatriculaDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new MatriculaDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Matricula obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhuma matricula fornecido");
    }

    private MatriculaDTO salvar(Matricula obj) {

        if (obj.getDesconto() == null) {
            obj.setDesconto(0f);
        }

        obj.setValorFinal(obj.getValor() - obj.getDesconto());

        AlunoDTO al = this.service.getAlunoById(obj.getAluno().getCodigo());
        al.setValorMensalidade(obj.getValorFinal());

        try {
            obj.setAluno(new Aluno(this.service.updateAluno(new Aluno(al))));
            Matricula m =  this.rep.save(obj);
            this.gerarMensalidades(m);
            return new MatriculaDTO(m);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }

    private void gerarMensalidades(Matricula obj) {
        Calendar dataInic = obj.getDataInic();
        Calendar dataFim = obj.getDataFim();
        Integer mes = (dataInic.get(Calendar.MONTH) + 1);
        Integer ano = dataInic.get(Calendar.YEAR);

        Integer inic = (dataInic.get(Calendar.MONTH) + 1);
        Integer fim = (((dataFim.get(Calendar.YEAR) - dataInic.get(Calendar.YEAR)) * 12 )
                + (dataFim.get(Calendar.MONTH) + 1));

        for (int i = inic; i <= fim; i++ ) {
            Mensalidade m = new Mensalidade();
            m.setAluno(obj.getAluno());
            m.setValor(obj.getValorFinal());

            if (mes > 12) {
                mes = 1;
                ano++;
            }

            m.setMes(mes.toString());
            m.setAno(ano.toString());
            m.setEstado(true);

            this.mensalidadeService.saveMensalidade(m);
            mes++;
        }


    }
}
