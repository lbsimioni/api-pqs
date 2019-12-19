package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.LucroDTO;
import br.com.altcompsystem.pqs.sistema.dtos.ProdutoDTO;
import br.com.altcompsystem.pqs.sistema.dtos.VendaDTO;
import br.com.altcompsystem.pqs.sistema.entity.Produto;
import br.com.altcompsystem.pqs.sistema.entity.Venda;
import br.com.altcompsystem.pqs.sistema.repository.VendaRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class VendaServiceImp implements VendaService {
    @Autowired
    private VendaRepository rep;

    @Autowired
    private LucroService service;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public VendaDTO getVendaById(Long id) {
        if (id <= 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Venda> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Venda não encontrada");
        }

        return new VendaDTO(opt.get());
    }

    @Override
    public List<VendaDTO> getVendas() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public VendaDTO saveVenda(Venda obj) {
        this.validar(obj);

        ProdutoDTO p = this.produtoService.getProdutoById(obj.getProduto().getCodigo());
        if((p.getEstoque() - obj.getQuantidade()) < p.getEstoqueMinimo()) {
            throw new BusinessException(Mensagens.SEM_ESTOQUE);
        }
        p.setEstoque(p.getEstoque() - obj.getQuantidade());
        this.produtoService.updateProduto(new Produto(p));

        obj.setData(this.hoje());

        return this.salvar(obj);
    }

    private Calendar hoje() {
        Calendar hoje = Calendar.getInstance();

        hoje.set(hoje.get(Calendar.YEAR),
                hoje.get(Calendar.MONTH),
                hoje.get(Calendar.DATE),
                0, 0, 0);

        return hoje;
    }

    private void validar(Venda obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhuma venda fornecida");
    }

    private List<VendaDTO> converterLista(List<Venda> lstOriginal){
        List<VendaDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new VendaDTO(objEntity)));

        return lstConversao;
    }

    private VendaDTO salvar(Venda obj) {
        try {
            obj.setValorFinal(obj.getValorUnit() * obj.getQuantidade());
            this.rep.save(obj);
            service.saveMaterial(new VendaDTO(obj));
            return new VendaDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
