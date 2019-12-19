package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.service.AlunoService;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.dtos.VendaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Extra;
import br.com.altcompsystem.pqs.sistema.entity.Lucro;
import br.com.altcompsystem.pqs.sistema.repository.LucroRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class LucroServiceImp implements LucroService {
    @Autowired
    private LucroRepository rep;

    @Autowired
    private RendimentoService rendimentoService;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private ExtraService extraService;

    @Override
    public LucroDTO getLucroById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Lucro> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Lucro não encontrado");
        }

        return new LucroDTO(opt.get());

    }

    @Override
    public LucroDTO getLucroByDate(String mes, String ano) {
        if ((mes == null) || mes.equals("") || (ano == null) || (ano.equals("")) ){
            throw new BusinessException("Data Inválido");
        }

        try {
            Lucro lucro = rep.findByMesAndAno(mes, ano);

            if (lucro.equals("") || (lucro.getCodigo() == null)){
                throw new BusinessException("Despesa não encontrada");
            }

            return new LucroDTO(lucro);
        } catch (Exception ex) {
            Lucro obj = new Lucro();
            obj.setMes(mes);
            obj.setAno(ano);
            return this.saveLucro(obj);
        }


    }

    @Override
    public List<LucroDTO> getLucros() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public LucroDTO saveLucro(Lucro obj) {
        this.validar(obj);

        if (this.rep.findByMesAndAno(obj.getMes(), obj.getAno()) != null){
            Lucro l = this.rep.findByMesAndAno(obj.getMes(), obj.getAno());
            obj.setCodigo(l.getCodigo());
            return this.updateLucro(obj);
        }

        return this.salvar(obj);
    }

    @Override
    public LucroDTO saveMaterial(VendaDTO obj) {
        Calendar d = obj.getData();
        String ano = d.get(Calendar.YEAR) + "";
        String mes = (d.get(Calendar.MONTH) + 1) + "";
        LucroDTO l = this.getLucroByDate(mes, ano);
        l.setRecebimentoMaterial(l.getRecebimentoMaterial() + obj.getValorFinal());
        return this.salvar(new Lucro(l));
    }

    @Override
    public LucroDTO updateLucro(Lucro obj) {
        this.validar(obj);

        LucroDTO l = this.getLucroById(obj.getCodigo());

        obj.setMes(l.getMes());
        obj.setAno(l.getAno());

        return this.salvar(obj);
    }

    @Override
    public LucroDTO deleteLucro(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Lucro> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Lucro não encontrado");
        }

        this.rep.deleteById(id);
        return new LucroDTO(opt.get());

    }

    @Override
    public LucroDTO updateByDate(String mes, String ano) {
        LucroDTO l = this.getLucroByDate(mes, ano);
        return this.saveLucro(new Lucro(l));
    }

    @Override
    public LucroDTO saveLucroExtra(Lucro obj) {

        LucroDTO lucro = this.getLucroById(obj.getCodigo());
        System.out.println(lucro);

        obj.getExtraList().forEach(extra -> {
            lucro.getExtraList().add(new Extra(this.extraService.saveExtra(extra)));
        });

        return this.salvar(new Lucro(lucro));
    }

    @Override
    public LucroDTO updateLucroExtra(Lucro obj) {
        LucroDTO lucro = this.getLucroById(obj.getCodigo());

        obj.getExtraList().forEach(extra -> {
            this.extraService.updateExtra(extra);
        });

        return this.updateLucro(new Lucro(lucro));
    }

    @Override
    public LucroDTO deleteLucroExtra(Long codigo, Long codigoExtra) {
        LucroDTO desp = this.getLucroById(codigo);
        desp.getExtraList().remove(new Extra(this.extraService.getExtraById(codigoExtra)));
        this.updateLucro(new Lucro(desp));
        this.extraService.deleteExtra(codigoExtra);
        return desp;
    }

    private List<LucroDTO> converterLista(List<Lucro> lstOriginal){
        List<LucroDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new LucroDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Lucro obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhum lucro fornecido");
    }

    private Float calcularLucro(Lucro obj) {

        Float total = 0f;

        if (obj.getRecebimentoMensalidade() == null) {
            obj.setRecebimentoMensalidade(0f);
        }

        if (obj.getRecebimentoMaterial() == null) {
            obj.setRecebimentoMaterial(0f);
        }

        if (obj.getValorExtra() == null) {
            obj.setValorExtra(0f);
        }

        total += obj.getRecebimentoMensalidade()
                + obj.getRecebimentoMaterial()
                + obj.getValorExtra();

        return total;
    }

    private Float calcularExtra(Lucro obj) {
        Float total = 0f;

        if (obj.getExtraList() != null) {
            for (int i = 0; i < obj.getExtraList().size(); i++){
                total += obj.getExtraList().get(i).getValor();
            }
        }

        return total;

    }

    private LucroDTO salvar(Lucro obj) {
        obj.setValorExtra(this.calcularExtra(obj));
        if (obj.getRecebimentoMaterial() == null) {
            obj.setRecebimentoMaterial(0f);
        }

        obj.setTotal(this.calcularLucro(obj));

        try {
            this.rep.save(obj);
            this.rendimentoService.saveLucro(new LucroDTO(obj));
            return new LucroDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }

}
