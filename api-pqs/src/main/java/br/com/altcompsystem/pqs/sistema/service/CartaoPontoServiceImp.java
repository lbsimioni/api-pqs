package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.sistema.dtos.CartaoPontoDTO;
import br.com.altcompsystem.pqs.sistema.entity.CartaoPonto;
import br.com.altcompsystem.pqs.sistema.repository.CartaoPontoRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class CartaoPontoServiceImp implements CartaoPontoService {
    @Autowired
    private CartaoPontoRepository rep;

    @Override
    public CartaoPontoDTO getCartaoPontoById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<CartaoPonto> opt = rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Cartão Ponto não encontrado");
        }

        return new CartaoPontoDTO(opt.get());

    }

    @Override
    public List<CartaoPontoDTO> getCartoesPontos() {
        return this.converterLista(this.rep.findAll());
    }

    @Override
    public CartaoPontoDTO saveCartaoPonto(CartaoPonto obj) {
        if (obj.getCodigo() != null) {
            return this.updateCartaoPonto(obj);
        }
        this.validar(obj);

        return this.salvar(obj);
    }

    @Override
    public CartaoPontoDTO updateCartaoPonto(CartaoPonto obj) {
        this.validar(obj);

        return this.salvar(obj);
    }

    @Override
    public CartaoPontoDTO deleteCartaoPonto(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<CartaoPonto> opt = this.rep.findById(id);

        if (opt.isPresent()){
            throw new BusinessException("Cartão Ponto não encontrado");
        }

        this.rep.deleteById(id);
        return new CartaoPontoDTO(opt.get());
    }

    private List<CartaoPontoDTO> converterLista(List<CartaoPonto> lstOriginal){
        List<CartaoPontoDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new CartaoPontoDTO(objEntity)));

        return lstConversao;
    }

    private void validar(CartaoPonto obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhum cartão ponto fornecido");
    }

    private CartaoPontoDTO salvar(CartaoPonto obj) {
        obj.setCargaHoraria(obj.getHorarioFim() - obj.getHorarioInic());
        try {
            this.rep.save(obj);
            return new CartaoPontoDTO(obj);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }

}
