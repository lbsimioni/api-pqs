package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.dtos.MensalidadeDTO;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import br.com.altcompsystem.pqs.sistema.entity.Mensalidade;
import br.com.altcompsystem.pqs.sistema.repository.MensalidadeRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MensalidadeServiceImp implements MensalidadeService {
    @Autowired
    private MensalidadeRepository rep;

    @Autowired
    private LucroService service;

    @Override
    public MensalidadeDTO getMensalidadeById(Long id) {
        if ((id == null) || (id <= 0)) {
            throw new BusinessException(Mensagens.OBJETO_NULO);
        }

        Optional<Mensalidade> opt = this.rep.findById(id);

        if (!opt.isPresent()) {
            throw new BusinessException(Mensagens.NAO_ENCONTRADO);
        }

        return new MensalidadeDTO(opt.get());
    }

    @Override
    public List<MensalidadeDTO> getMensalidades() {
        return this.converterLista(this.rep.findByEstado(true));
    }

    @Override
    public MensalidadeDTO saveMensalidade(Mensalidade obj) {
        if (obj.getCodigo() != null) {
            return this.updateMensalidade(obj);
        }

        Optional<Mensalidade> opt = this.rep.findByAlunoAndMesAndAno(obj.getAluno(), obj.getMes(), obj.getAno());

        if (opt.isPresent()) {
            Mensalidade m = opt.get();
            m.setValor(obj.getValor());
            return this.updateMensalidade(m);
        }

        this.validar(obj);

        return this.salvar(obj);
    }

    @Override
    public MensalidadeDTO updateMensalidade(Mensalidade obj) {
        this.validar(obj);

        return this.salvar(obj);
    }

    @Override
    public MensalidadeDTO pagar(Long id) {
        Mensalidade m = new Mensalidade(this.getMensalidadeById(id));

        m.setEstado(false);

        LucroDTO l = this.service.getLucroByDate(m.getMes(), m.getAno());
        if (l.getRecebimentoMensalidade() == null) {
            l.setRecebimentoMensalidade(0f);
        }
        l.setRecebimentoMensalidade(l.getRecebimentoMensalidade() + m.getValor());
        this.service.updateLucro(new Lucro(l));
        return this.salvar(m);
    }

    private List<MensalidadeDTO> converterLista(List<Mensalidade> lstOriginal){
        List<MensalidadeDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new MensalidadeDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Mensalidade obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException(Mensagens.OBJETO_NULO);
    }

    private MensalidadeDTO salvar(Mensalidade obj) {

        if (Integer.parseInt(obj.getMes()) < 10) {
            obj.setMes("0" + obj.getMes());
        }

        try {
            return new MensalidadeDTO(this.rep.save(obj));
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
