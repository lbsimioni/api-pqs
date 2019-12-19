package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.EntradaDTO;
import br.com.altcompsystem.pqs.sistema.dtos.ProdutoDTO;
import br.com.altcompsystem.pqs.sistema.entity.Entrada;
import br.com.altcompsystem.pqs.sistema.entity.Produto;
import br.com.altcompsystem.pqs.sistema.repository.EntradaRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaServiceImp implements EntradaService {
    @Autowired
    private EntradaRepository rep;

    @Autowired
    private DespesaService service;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public EntradaDTO getEntradaById(Long id) {
        if (id <= 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Entrada> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Entrada não encontrada");
        }

        return new EntradaDTO(opt.get());
    }

    @Override
    public List<EntradaDTO> getEntradas() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public EntradaDTO saveEntrada(Entrada obj) {
        this.validar(obj);

        ProdutoDTO p = this.produtoService.getProdutoById(obj.getProduto().getCodigo());

        p.setEstoque(p.getEstoque() + obj.getQuantidade());
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

    private void validar(Entrada obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhuma entrada fornecida");
    }

    private List<EntradaDTO> converterLista(List<Entrada> lstOriginal){
        List<EntradaDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new EntradaDTO(objEntity)));

        return lstConversao;
    }

    private EntradaDTO salvar(Entrada obj) {
        try {
            obj.setValorFinal(obj.getValorUnit() * obj.getQuantidade());
            this.rep.save(obj);
            this.service.saveMaterial(new EntradaDTO(obj));
            return new EntradaDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
