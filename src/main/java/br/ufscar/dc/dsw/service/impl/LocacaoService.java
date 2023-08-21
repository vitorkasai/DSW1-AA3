package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@Service
@Transactional(readOnly = false)
public class LocacaoService implements ILocacaoService {

	@Autowired
	ILocacaoDAO daoLocacao;
	
	public void salvar(Locacao locacao) {
		daoLocacao.save(locacao);
	}
	
	@Transactional(readOnly = true)
    public List<Locacao> buscarTodos(){
        return daoLocacao.findAll();
    }

	@Transactional(readOnly = true)
	public Locacao buscarPorId(Long id) {
		return daoLocacao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Locacao> buscarTodosUsuario(Usuario u) {
		return daoLocacao.findAllByUsuario(u);
	}
    @Transactional(readOnly = true)
	public List<Locacao> buscarTodosLocadora(Locadora l) {
		return daoLocacao.findAllByLocadora(l);
	}
    
}