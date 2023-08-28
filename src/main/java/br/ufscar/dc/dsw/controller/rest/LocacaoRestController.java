package br.ufscar.dc.dsw.controller.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}


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