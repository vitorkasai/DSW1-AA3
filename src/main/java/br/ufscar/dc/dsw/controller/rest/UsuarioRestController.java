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
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@CrossOrigin
@RestController
public class UsuarioRestController {

	@Autowired
	private IUsuarioService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Usuario usuario, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				usuario.setId(((Integer) id).longValue());
			} else {
				usuario.setId((Long) id);
			}
		}

		usuario.setCPF((String) json.get("cpf"));
		usuario.setDataNascimento((String) json.get("dataNascimento"));
		usuario.setNome((String) json.get("nome"));
		usuario.setPassword(new BCryptPasswordEncoder().encode((String) json.get("password")));
		usuario.setEnabled(true);
		usuario.setRole("ROLE_USER");
		usuario.setSexo((String) json.get("sexo"));
		usuario.setTelefone((String) json.get("telefone"));
		usuario.setUsername((String) json.get("username"));
	}

	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Usuario>> lista() {
		List<Usuario> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Usuario> lista(@PathVariable("id") long id) {
		Usuario usuario = service.buscarPorId(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}

	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Usuario> cria(@RequestBody JSONObject json) {
		try {
			System.out.println("JSON: ");
			System.out.println(json);
			if (isJSONValid(json.toString())) {
				String cpf = (String) json.get("cpf");
				Usuario existingUser = service.buscarPorCPF(cpf); // Adicione um método personalizado para buscar por CPF
				
				if (existingUser != null) {
					// Se o usuário com o mesmo CPF já existe, retorne um erro ou uma resposta apropriada
					return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Conflito de dados (CPF já existe)
				}
				
				Usuario usuario = new Usuario();
				parse(usuario, json);
				service.salvar(usuario);
				return ResponseEntity.ok(usuario);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Usuario> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Usuario usuario = service.buscarPorId(id);
				if (usuario == null) {
					return ResponseEntity.notFound().build();
				} else {
					String cpf = (String) json.get("cpf");
					Usuario existingUser = service.buscarPorCPF(cpf); // Adicione um método personalizado para buscar por CPF
					
					if (existingUser != null && !existingUser.getId().equals(usuario.getId())) {
						// Se o usuário com o mesmo CPF já existe (diferente do usuário sendo atualizado), retorne um erro
						return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Conflito de dados (CPF já existe)
					}
					
					parse(usuario, json);
					service.salvar(usuario);
					return ResponseEntity.ok(usuario);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}


	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Usuario usuario = service.buscarPorId(id);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return ResponseEntity.noContent().build();
		}
	}
}