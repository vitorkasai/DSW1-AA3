package br.ufscar.dc.dsw.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;


@CrossOrigin
@RestController
public class LocacaoRestController {

	@Autowired
	private ILocacaoService serviceLocacao;

    @Autowired
	private IUsuarioService serviceUsuario;

    @Autowired
	private ILocadoraService serviceLocadora;


	@GetMapping(path = "/locacoes")
	public ResponseEntity<List<Locacao>> lista() {
		List<Locacao> lista = serviceLocacao.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/locacoes/{id}")
	public ResponseEntity<Locacao> lista(@PathVariable("id") long id) {
		Locacao locacao = serviceLocacao.buscarPorId(id);
		if (locacao == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locacao);
	}

    
	@GetMapping(path = "/locacoes/clientes/{id}")
	public ResponseEntity<List<Locacao>> listaPorCliente(@PathVariable("id") long id) {
		
        Usuario usuario = serviceUsuario.buscarPorId(id);
		List<Locacao> locacoes = serviceLocacao.buscarTodosUsuario(usuario);
		
		if (locacoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locacoes);
	} 

    
    @GetMapping(path = "/locacoes/locadoras/{id}")
	public ResponseEntity<List<Locacao>> listaPorLocadora(@PathVariable("id") long id) {

		Locadora locadora = serviceLocadora.buscarPorId(id);
		List<Locacao> locacoes = serviceLocacao.buscarTodosLocadora(locadora);
		
		if (locacoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locacoes);
	} 
    
}