package br.com.altcompsystem.pqs.pessoas.service;

import br.com.altcompsystem.pqs.error.AuthenticationException;
import br.com.altcompsystem.pqs.error.BusinessException;
import br.com.altcompsystem.pqs.pessoas.dtos.FuncionarioDTO;
import br.com.altcompsystem.pqs.pessoas.dtos.LoginDTO;
import br.com.altcompsystem.pqs.pessoas.entity.Contato;
import br.com.altcompsystem.pqs.pessoas.entity.Endereco;
import br.com.altcompsystem.pqs.pessoas.entity.Funcionario;
import br.com.altcompsystem.pqs.pessoas.repository.FuncionarioRepository;
import br.com.altcompsystem.pqs.sistema.entity.BancoHoras;
import br.com.altcompsystem.pqs.sistema.service.BancoHorasService;
import br.com.altcompsystem.pqs.utils.Mensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImp implements FuncionarioService {
    @Autowired
    private FuncionarioRepository rep;

    @Autowired
    EnderecoService enderecoService;

    @Autowired
    ContatoService contatoService;

    @Autowired
    BancoHorasService bancoHorasService;

    @Override
    public FuncionarioDTO getFuncionarioById(Long id) {
        if (id < 0){
            throw new BusinessException("Código Inválido");
        }

        Optional<Funcionario> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Funcionário não encontrado");
        }

        return new FuncionarioDTO(opt.get());
    }

    @Override
    public FuncionarioDTO getFuncionarioByRg(String rg) {

        Optional<Funcionario> opt = this.rep.findByRg(rg);

        if (!opt.isPresent()){
            throw new BusinessException("Funcionário não encontrado");
        }

        return new FuncionarioDTO(opt.get());
    }

    @Override
    public List<FuncionarioDTO> getFuncionarioByName(String name) {
        return this.converterLista(this.rep.findByNomeIgnoreCaseContaining(name));
    }

    @Override
    public List<FuncionarioDTO> getFuncionarios() {
        return this.converterLista(this.rep.findByEstado(true));
    }

    @Override
    public FuncionarioDTO saveFuncionario(Funcionario objFunc) {
        this.validar(objFunc);

        if(this.rep.findByRg(objFunc.getRg()).isPresent()){
            throw new BusinessException("RG já registrado!");
        }

        if (this.rep.findByCpf(objFunc.getCpf()) != null){
            throw new BusinessException("CPF já registrado!");
        }

        if (this.rep.findByUsername(objFunc.getUsername()) != null){
            throw new BusinessException("Usuário inválido");
        }

        Calendar calendar = Calendar.getInstance();

        objFunc.setEstado(true);
        objFunc.setDataEntrada(calendar);
        objFunc =  new Funcionario(this.salvar(objFunc));
        this.bancoHorasService.saveBancoHoras(objFunc);
        return new FuncionarioDTO(objFunc);
    }

    @Override
    public FuncionarioDTO updateFuncionario(Funcionario objFunc) {
        this.validar(objFunc);

        FuncionarioDTO func = this.getFuncionarioById(objFunc.getCodigo());

        if ((objFunc.getPassword() != null) || (!objFunc.getPassword().equals("")) ){
            objFunc.setPassword(func.getPassword());
        }

        if (!objFunc.getRg().equals(func.getRg())){
            if (this.rep.findByRg(objFunc.getRg()) != null){
                throw new BusinessException("RG já cadastrado");
            }
        }

        if (!objFunc.getCpf().equals(func.getCpf())){
            if (this.rep.findByCpf(objFunc.getCpf()) != null){
                throw new BusinessException("CPF já cadastrado");
            }
        }

        objFunc.setUsername(func.getUsername());
        objFunc.setEstado(func.getEstado());
        objFunc.setDataEntrada(func.getDataEntrada());

        return this.salvar(objFunc);
    }

    @Override
    public FuncionarioDTO mudarEstadoFuncionario(Long id) {
        if (id < 0) {
            throw new BusinessException("Código Inválido");
        }

        Optional<Funcionario> opt = this.rep.findById(id);

        if (!opt.isPresent()){
            throw new BusinessException("Funcionário não encontrado");
        }

        Funcionario func = opt.get();
        func.setEstado(!func.getEstado());

        try {
            this.rep.save(func);
            return new FuncionarioDTO(func);
        } catch (DataAccessException ex){
            throw new BusinessException("Erro ao salvar no banco de dados");
        }

    }

    @Override
    public FuncionarioDTO autenticar(LoginDTO objLogin) {
        Optional<Funcionario> optFuncionario = this.rep.findByUsernameAndPassword(objLogin.getUsuario(), objLogin.getPassword());

        if (optFuncionario.isPresent())
            return new FuncionarioDTO(optFuncionario.get());

        throw new BusinessException(Mensagens.FALHA_LOGIN);
    }

    private List<FuncionarioDTO> converterLista(List<Funcionario> lstOriginal){
        List<FuncionarioDTO> lstConversao = new ArrayList<>();

        if (lstOriginal == null)
            return lstConversao;

        lstOriginal.forEach(objEntity -> lstConversao.add(new FuncionarioDTO(objEntity)));

        return lstConversao;
    }

    private void validar(Funcionario obj){
        if ((obj == null) || (obj.equals("")))
            throw new BusinessException("Nenhum funcionário fornecido");
    }

    private FuncionarioDTO salvar(Funcionario objFunc) {
        try{
            objFunc.setEndereco(new Endereco(this.enderecoService.saveEndereco(objFunc.getEndereco())));
            objFunc.setContato(new Contato(this.contatoService.saveContato(objFunc.getContato())));

            this.rep.save(objFunc);
            return new FuncionarioDTO(objFunc);
        } catch (DataAccessException ex){
            throw new BusinessException(Mensagens.ERRO_PERSISTENCIA);
        }
    }
}
