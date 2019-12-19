package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.FuncionarioDTO;
import br.com.altcompsystem.pqs.pessoas.service.FuncionarioService;
import br.com.altcompsystem.pqs.sistema.dtos.DespesaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.EntradaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Despesa;
import br.com.altcompsystem.pqs.sistema.entity.Extra;
import br.com.altcompsystem.pqs.sistema.repository.DespesaRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaServiceImp implements DespesaService {
    @Autowired
    private DespesaRepository rep;

    @Autowired
    private FuncionarioService service;

    @Autowired
    private ExtraService extraService;

    @Autowired
    private RendimentoService rendimentoService;

    @Override
    public DespesaDTO getDespesaById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Despesa> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Despesa não encontrada");
        }

        return new DespesaDTO(opt.get());

    }

    @Override
    public DespesaDTO getDespesaByDate(String mes, String ano) {
        if ((mes == null) || mes.equals("") || (ano == null) || (ano.equals("")) ){
            throw new BusinessException("Data Inválido");
        }

        Despesa despesa = rep.findByMesAndAno(mes, ano);

        if (despesa == null || despesa.equals("") || (despesa.getCodigo() == null) || despesa.equals("") ){
            DespesaDTO desp = new DespesaDTO();
            desp.setMes(mes);
            desp.setAno(ano);
            return this.salvar(new Despesa(desp));
        }

        return new DespesaDTO(despesa);

    }

    @Override
    public List<DespesaDTO> getDespesas() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public DespesaDTO saveDespesa(Despesa obj) {
        if (this.rep.findByMesAndAno(obj.getMes(), obj.getAno()) != null){
            Despesa d = this.rep.findByMesAndAno(obj.getMes(), obj.getAno());
            obj.setCodigo(d.getCodigo());
            return this.updateDespesa(obj);
        }
        this.validar(obj);

        if (obj.getValorExtra() == null){
            obj.setValorExtra(0f);
        }

        return this.salvar(obj);

    }

    @Override
    public DespesaDTO saveMaterial(EntradaDTO obj) {
        Calendar d = obj.getData();
        String ano = d.get(Calendar.YEAR) + "";
        String mes = (d.get(Calendar.MONTH) + 1) + "";
        DespesaDTO desp = this.getDespesaByDate(mes, ano);

        if (desp.getMaterial() == null) {
            desp.setMaterial(0f);
        }

        desp.setMaterial(desp.getMaterial() + obj.getValorFinal());
        return this.saveDespesa(new Despesa(desp));
    }

    @Override
    public DespesaDTO updateDespesa(Despesa obj) {
        this.validar(obj);

        DespesaDTO desp = this.getDespesaById(obj.getCodigo());

        obj.setMes(desp.getMes());
        obj.setAno(desp.getAno());

        return this.salvar(obj);
    }

    @Override
    public DespesaDTO deleteDespesa(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Despesa> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Despesa não encontrada");
        }

        this.rep.deleteById(id);
        return new DespesaDTO(opt.get());
    }

    @Override
    public DespesaDTO saveDespesaExra(Despesa obj) {

        DespesaDTO desp = this.getDespesaById(obj.getCodigo());

        obj.getExtraList().forEach(extra -> {
            desp.getExtraList().add(new Extra(this.extraService.saveExtra(extra)));
        });

        return this.salvar(new Despesa(desp));
    }

    @Override
    public DespesaDTO updateDespesaExtra(Despesa obj) {
        DespesaDTO desp = this.getDespesaById(obj.getCodigo());

        obj.getExtraList().forEach(extra -> {
            this.extraService.updateExtra(extra);
        });

        return this.salvar(new Despesa(desp));
    }

    @Override
    public DespesaDTO deleteDespesaExtra(Long codigo, Long codigoExtra) {
        DespesaDTO desp = this.getDespesaById(codigo);
        desp.getExtraList().remove(new Extra(this.extraService.getExtraById(codigoExtra)));
        this.updateDespesa(new Despesa(desp));
        this.extraService.deleteExtra(codigoExtra);
        return desp;
    }

    private List<DespesaDTO> converterLista(List<Despesa> lstOriginal){
        List<DespesaDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new DespesaDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Despesa obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhuma despesa fornecida");
    }

    private Float calcularDespesas(Despesa obj) {

        Float total = 0f;

        if (obj.getContAgua() == null) {
            obj.setContAgua(0f);
        }

        if (obj.getContEnergia() == null) {
            obj.setContEnergia(0f);
        }

        if (obj.getContInternet() == null) {
            obj.setContInternet(0f);
        }

        if (obj.getMaterial() == null) {
            obj.setMaterial(0f);
        }

        if (obj.getValorExtra() == null){
            obj.setValorExtra(0f);
        }

        if (obj.getCompra() == null) {
            obj.setCompra(0f);
        }

        total += obj.getPagamentoFunc()
                + obj.getCompra()
                + obj.getContAgua()
                + obj.getContEnergia()
                + obj.getContInternet()
                + obj.getMaterial()
                + obj.getValorExtra();

        return total;

    }

    private Float calcularExtra(Despesa obj) {
        Float total = 0f;

        if (obj.getExtraList() != null) {
            for (int i = 0; i < obj.getExtraList().size(); i++){
                total += obj.getExtraList().get(i).getValor();
            }
        }

        return total;

    }

    private Float calcularPagamento(Despesa obj) {
        Integer mes = Integer.parseInt(obj.getMes());
        Integer ano = Integer.parseInt(obj.getAno());

        Float total = 0f;

        List<FuncionarioDTO> lst = this.service.getFuncionarios();
        obj.setPagamentoFunc(0f);

        if (lst.size() > 0) {
            for (int i = 0; i < lst.size(); i++){
                FuncionarioDTO f = lst.get(i);
                if (ano > f.getDataEntrada().get(Calendar.YEAR)){
                    total += f.getSalario();
                } else if (ano == f.getDataEntrada().get(Calendar.YEAR)){
                    if (mes >= (f.getDataEntrada().get(Calendar.MONTH) + 1)){
                        total += f.getSalario();
                    }
                }
            }
        }

        return total;
    }

    private DespesaDTO salvar(Despesa obj) {
        obj.setValorExtra(this.calcularExtra(obj));
        obj.setPagamentoFunc(this.calcularPagamento(obj));
        obj.setTotal(this.calcularDespesas(obj));

        try {
            this.rep.save(obj);
            this.rendimentoService.saveDespesa(new DespesaDTO(obj));
            return new DespesaDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }

}
