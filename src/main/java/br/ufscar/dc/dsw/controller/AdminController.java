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
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IUsuarioService serviceUsuario;
	@Autowired
	private ILocadoraService serviceLocadora;
    
    @GetMapping("/crud-clientes")
	public String listarClientes(ModelMap model) {
		System.out.println("Passei por /admin/crud-clientes");
		model.addAttribute("usuarios",serviceUsuario.buscarTodos());
		return "admin/crud_clientes";
	}
	
	@GetMapping("/crud-locadoras")
	public String listarLocadoras(ModelMap model) {
		System.out.println("Passei por /admin/crud-locadoras");
		model.addAttribute("locadoras",serviceLocadora.buscarTodos());
		return "admin/crud_locadoras";
	}
	
}
