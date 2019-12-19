package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.ProdutoDTO;
import br.com.altcompsystem.pqs.sistema.entity.Produto;
import br.com.altcompsystem.pqs.sistema.repository.ProdutoRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImp implements ProdutoService {
    @Autowired
    private ProdutoRepository rep;

    @Override
    public ProdutoDTO getProdutoById(Long id) {
        if ((id == null) || (id <= 0)) {
            throw new BusinessException(Mensagens.OBJETO_NULO);
        }

        Optional<Produto> opt = this.rep.findById(id);

        if (!opt.isPresent()) {
            throw new BusinessException(Mensagens.NAO_ENCONTRADO);
        }

        return new ProdutoDTO(opt.get());
    }

    @Override
    public List<ProdutoDTO> getProdutoByName(String nome) {
        return this.converterLista(this.rep.findByNomeIgnoreCaseContaining(nome));
    }

    @Override
    public List<ProdutoDTO> getProdutos() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public ProdutoDTO saveProduto(Produto obj) {
        this.validar(obj);

        return this.salvar(obj);
    }

    @Override
    public ProdutoDTO updateProduto(Produto obj) {
        this.validar(obj);
        return this.salvar(obj);
    }

    @Override
    public ProdutoDTO deleteProduto(Long id) {
        return null;
    }

    private List<ProdutoDTO> converterLista(List<Produto> lstOriginal){
        List<ProdutoDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new ProdutoDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Produto obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException(Mensagens.OBJETO_NULO);
    }

    private ProdutoDTO salvar(Produto obj) {
        try {
            return new ProdutoDTO(this.rep.save(obj));
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
