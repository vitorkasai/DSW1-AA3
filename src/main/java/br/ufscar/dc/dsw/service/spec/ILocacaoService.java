package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;

public interface ILocacaoService {

	Locacao buscarPorId(Long id);

	List<Locacao> buscarTodos();
	List<Locacao> buscarTodosUsuario(Usuario u);
    List<Locacao> buscarTodosLocadora(Locadora l);

	void salvar(Locacao locacao);
}