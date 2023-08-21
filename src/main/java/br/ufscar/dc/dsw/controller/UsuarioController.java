package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Usuario usuario) {
		System.out.println("Passei por /usuarios/cadastrar");
		return "user/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("usuarios",service.buscarTodos());
		return "admin/crud_clientes";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "user/cadastro";
		}

		System.out.println("password = " + usuario.getPassword());
		
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		service.salvar(usuario);
		attr.addFlashAttribute("sucess", "Usuário inserido com sucesso.");
		return "redirect:/admin/crud-clientes";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		System.out.println("Passei por /usuarios/editar/id especifico");
		model.addAttribute("usuario", service.buscarPorId(id));
		return "user/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		System.out.println("Passei por /usuarios/editar");

		if (result.getFieldErrorCount() > 1 || result.getFieldError("CPF") == null) {
			return "user/cadastro";
		}

		System.out.println(usuario.getPassword());
		
		service.salvar(usuario);
		attr.addFlashAttribute("sucess", "Usuário editado com sucesso.");
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		service.excluir(id);
		attr.addFlashAttribute("sucess", "Usuário excluído com sucesso.");
		return "redirect:/usuarios/listar";
	}
}