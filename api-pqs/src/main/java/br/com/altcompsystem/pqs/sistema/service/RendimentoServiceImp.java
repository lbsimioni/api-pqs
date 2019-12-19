package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.DespesaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.dtos.RendimentoDTO;
import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import br.com.altcompsystem.pqs.sistema.entity.Rendimento;
import br.com.altcompsystem.pqs.sistema.repository.RendimentoRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RendimentoServiceImp implements RendimentoService {
    @Autowired
    private RendimentoRepository rep;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private LucroService lucroService;

    @Override
    public RendimentoDTO getRendimentoById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Rendimento> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Rendimento não encontrado");
        }

        return new RendimentoDTO(opt.get());

    }

    @Override
    public RendimentoDTO getRendimentoByDate(String mes, String ano) {
        if ((mes == null) || mes.equals("") || (ano == null) || (ano.equals("")) ){
            throw new BusinessException("Data Inválido");
        }

        try {
            Rendimento rendimento = rep.findByMesAndAno(mes, ano);
            return new RendimentoDTO(rendimento);

        } catch (Exception ex) {
            RendimentoDTO rendimento = new RendimentoDTO();
            rendimento.setValorLucro(0f);
            rendimento.setValorDespesa(0f);
            rendimento.setTotal(0f);
            rendimento.setMes(mes);
            rendimento.setAno(ano);
            return rendimento;
        }
    }

    @Override
    public List<RendimentoDTO> getRendimentos() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public RendimentoDTO saveRendimento(Rendimento obj) {
        this.validar(obj);

        if (this.rep.findByMesAndAno(obj.getMes(), obj.getAno()) != null){
            Rendimento r = this.rep.findByMesAndAno(obj.getMes(), obj.getAno());
            obj.setCodigo(r.getCodigo());
            return this.updateRendimento(obj);
        }

        if (obj.getDespesa() == null) {
            obj.setDespesa(new Despesa(this.despesaService.getDespesaByDate(obj.getMes(), obj.getAno())));
        }

        if (obj.getLucro() == null) {
            obj.setLucro(new Lucro(this.lucroService.getLucroByDate(obj.getMes(), obj.getAno())));
        }

        return this.salvar(obj);
    }

    @Override
    public RendimentoDTO updateRendimento(Rendimento obj) {
        this.validar(obj);

        RendimentoDTO rend = this.getRendimentoByDate(obj.getMes(), obj.getAno());
        obj.setCodigo(rend.getCodigo());

        if (obj.getDespesa() == null) {
            obj.setDespesa(rend.getDespesa());
            obj.setValorDespesa(rend.getValorDespesa());
        }

        if (obj.getLucro() == null) {
            obj.setLucro(rend.getLucro());
            obj.setValorLucro(rend.getValorLucro());
        }

        return this.salvar(obj);
    }

    @Override
    public RendimentoDTO deleteRendimento(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Rendimento> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Rendimento não encontrado");
        }

        this.rep.deleteById(id);
        return new RendimentoDTO(opt.get());
    }

    @Override
    public RendimentoDTO saveDespesa(DespesaDTO obj) {
        RendimentoDTO rend = new RendimentoDTO();

        rend.setMes(obj.getMes());
        rend.setAno(obj.getAno());
        rend.setDespesa(new Despesa(obj));

        rend.setValorDespesa(obj.getTotal());

        return this.saveRendimento(new Rendimento(rend));
    }

    @Override
    public RendimentoDTO saveLucro(LucroDTO obj) {
        RendimentoDTO rend = new RendimentoDTO();

        rend.setMes(obj.getMes());
        rend.setAno(obj.getAno());
        if (obj.getCodigo() == null) {
            obj = this.lucroService.saveLucro(new Lucro(obj));
        }

        rend.setLucro(new Lucro(obj));

        rend.setValorLucro(obj.getTotal());
        return this.saveRendimento(new Rendimento(rend));
    }

    @Override
    public List<RendimentoDTO> getSemestre() {
        Calendar calendar = Calendar.getInstance();
        List<String> meses = new ArrayList<>();
        List<String> anos = new ArrayList<>();
        List<RendimentoDTO> lstRendimento = new ArrayList<>();

        int atualMesesInt = (calendar.get(Calendar.MONTH) + 1);
        int atualAno = (calendar.get(Calendar.YEAR));
        String atual;

        for (int i = 0; i < 6; i++){
            if (atualMesesInt < 1) {
                atualMesesInt = 12;
                atualAno--;
            }

            if (atualMesesInt < 10) {
                atual = "0" + atualMesesInt;
            } else {
                atual = atualMesesInt + "";
            }

            meses.add(atual);
            anos.add(atualAno + "");

            atualMesesInt--;
        }

        for (int i = 5; i >= 0; i--) {
            lstRendimento.add(this.getRendimentoByDate(meses.get(i), anos.get(i)));
        }

        return lstRendimento;
    }

    private List<RendimentoDTO> converterLista(List<Rendimento> lstOriginal){
        List<RendimentoDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new RendimentoDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Rendimento obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhum lucro fornecido");
    }

    private RendimentoDTO salvar(Rendimento obj) {
        obj.setTotal(this.calcular(obj));
        try {
            this.rep.save(obj);
            return new RendimentoDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }

    private Float calcular(Rendimento obj) {
        if ((obj.getValorDespesa() == null)) {
            obj.setValorDespesa(0f);
        }

        if ((obj.getValorLucro() == null)) {
            obj.setValorLucro(0f);
        }

        return (obj.getValorLucro() - obj.getValorDespesa());
    }

}
