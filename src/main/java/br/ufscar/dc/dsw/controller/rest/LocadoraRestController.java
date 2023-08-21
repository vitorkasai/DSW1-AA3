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
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin
@RestController
public class LocadoraRestController {

	@Autowired
	private ILocadoraService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Locadora locadora, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				locadora.setId(((Integer) id).longValue());
			} else {
				locadora.setId((Long) id);
			}
		}
        
		locadora.setCNPJ((String) json.get("cnpj"));
		locadora.setCidade((String) json.get("cidade"));
		locadora.setNome((String) json.get("nome"));
		locadora.setPassword(new BCryptPasswordEncoder().encode((String) json.get("password")));
		locadora.setEnabled(true);
		locadora.setRole("ROLE_LOCADORA");
		locadora.setUsername((String) json.get("username"));
        
	}

	@GetMapping(path = "/locadoras")
	public ResponseEntity<List<Locadora>> lista() {
		List<Locadora> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/locadoras/{id}")
	public ResponseEntity<Locadora> lista(@PathVariable("id") long id) {
		Locadora locadora = service.buscarPorId(id);
		if (locadora == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locadora);
	}

	@PostMapping(path = "/locadoras")
	@ResponseBody
	public ResponseEntity<Locadora> cria(@RequestBody JSONObject json) {
		try {
			System.out.println("JSON: ");
			System.out.println(json);
			if (isJSONValid(json.toString())) {
				Locadora locadora = new Locadora();
				parse(locadora, json);

				service.salvar(locadora);
				return ResponseEntity.ok(locadora);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/locadoras/{id}")
	public ResponseEntity<Locadora> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Locadora locadora = service.buscarPorId(id);
				if (locadora == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(locadora, json);
					service.salvar(locadora);
					return ResponseEntity.ok(locadora);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/locadoras/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Locadora locadora = service.buscarPorId(id);
		if (locadora == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
    
	@GetMapping(path = "/locadoras/cidades/{nome}")
	public ResponseEntity<List<Locadora>> listaPorCidade(@PathVariable("nome") String cidade) {
		
		List<Locadora> locadoras = service.buscarTodosPorCidade(cidade);
		
		if (locadoras.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locadoras);
	} 
}