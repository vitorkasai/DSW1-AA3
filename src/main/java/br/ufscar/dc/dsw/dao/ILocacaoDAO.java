package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;

@SuppressWarnings("unchecked")
public interface ILocacaoDAO extends CrudRepository<Locacao, Long>{

	Locacao findById(long id);

	List<Locacao> findAll();
	List<Locacao> findAllByUsuario(Usuario u);
    List<Locacao> findAllByLocadora(Locadora l);
	
	Locacao save(Locacao locacao);
}