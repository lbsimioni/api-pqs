package br.com.altcompsystem.pqs.sistema.service;

import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.FuncionarioDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.pessoas.service.FuncionarioService;
import br.com.altcompsystem.pqs.sistema.dtos.BancoHorasDTO;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import br.com.altcompsystem.pqs.sistema.repository.BancoHorasRepository;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BancoHorasServiceImp implements BancoHorasService {
    @Autowired
    private BancoHorasRepository rep;

    @Autowired
    private CartaoPontoService service;

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public BancoHorasDTO getBancoHorasById(Long id) {
        if (id == null || id < 0) {
            throw new BusinessException("Codigo inválido");
        }

        Optional<BancoHoras> opt = this.rep.findById(id);

        if (!opt.isPresent()) {
            throw new BusinessException("Nenhum banco de horas encontrado");
        }

        return new BancoHorasDTO(opt.get());
    }

    @Override
    public BancoHorasDTO getBancoHorasByFunc(Funcionario obj) {

        Optional<BancoHoras> opt = this.rep.findByFuncionario(obj);

        if (!opt.isPresent()) {
            throw new BusinessException("Nenhum banco de horas encontrado");
        }

        return new BancoHorasDTO(opt.get());
    }

    @Override
    public List<BancoHorasDTO> getBancosHoras() {
        List<FuncionarioDTO> lstFunc = this.funcionarioService.getFuncionarios();
        List<BancoHorasDTO> lstBancoHoras = new ArrayList<>();

        lstFunc.forEach(func -> {
            lstBancoHoras.add(this.getBancoHorasByFunc(new Funcionario(func)));
        });

        return lstBancoHoras;
    }

    @Override
    public BancoHorasDTO saveBancoHoras(Funcionario func) {
        BancoHoras obj = new BancoHoras();

        obj.setHoras(0f);
        obj.setFuncionario(func);
        return this.salvar(obj);
    }

    @Override
    public BancoHorasDTO updateBancoHoras(BancoHoras obj) {
        this.validar(obj);
        obj.setFuncionario(this.getBancoHorasById(obj.getCodigo()).getFuncionario());

        if (obj.getLstCartaoPonto().size() > 0) {
            FuncionarioDTO func = this.funcionarioService.getFuncionarioById(obj.getFuncionario().getCodigo());

            obj.setHoras(0f);
            obj.getLstCartaoPonto().forEach(cartaoPonto -> {
                this.service.saveCartaoPonto(cartaoPonto);
                obj.setHoras(obj.getHoras() +
                        (
                                (cartaoPonto.getHorarioFim() - cartaoPonto.getHorarioInic())
                                - Float.parseFloat(func.getCargaHoraria())
                        )
                );
            });
        }

        return this.salvar(obj);

    }

    @Override
    public BancoHorasDTO saveCartaoPonto(BancoHoras obj) {
        this.validar(obj);

        FuncionarioDTO func = this.funcionarioService.getFuncionarioById(obj.getFuncionario().getCodigo());
        BancoHoras bh = new BancoHoras(this.getBancoHorasByFunc(new Funcionario(func)));

        obj.setCodigo(bh.getCodigo());
        obj.setHoras(bh.getHoras());

        obj.getLstCartaoPonto().forEach(cartaoPonto -> {
            bh.getLstCartaoPonto().add(cartaoPonto);
            this.service.saveCartaoPonto(cartaoPonto);
            bh.setHoras(bh.getHoras() +
                    (
                            (cartaoPonto.getHorarioFim() - cartaoPonto.getHorarioInic())
                                    - Float.parseFloat(func.getCargaHoraria())
                    )
            );
        });

        return this.salvar(bh);
    }

    private void validar(BancoHoras obj) {
        if ((obj == null) || obj.equals("")) {
            throw new BusinessException("Banco de horas não fornecido");
        }
    }

    private BancoHorasDTO salvar(BancoHoras obj) {
        try {
            this.rep.save(obj);
            return new BancoHorasDTO(obj);
        } catch (DataAccessException ex) {
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
