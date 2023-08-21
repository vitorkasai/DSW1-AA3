package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import javax.validation.Valid;

import java.nio.file.Files;
import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/locadoras")
public class LocadoraController {
	
	@Autowired
	private ILocadoraService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Locadora locadora) {
		System.out.println("Passei por /locadoras/cadastrar");
		return "locadora/cadastro";
	}
	//LISTAGEM DO CRUD
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		System.out.println("Passei por /locadoras/listar");
		model.addAttribute("locadoras",service.buscarTodos());
		return "admin/crud_locadoras";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
		System.out.println("Passei por /locadoras/salvar");
		if (result.hasErrors()) {
			return "locadora/cadastro";
		}

		System.out.println("password = " + locadora.getPassword());
		
		locadora.setPassword(encoder.encode(locadora.getPassword()));
		service.salvar(locadora);
		attr.addFlashAttribute("sucess", "Locadora inserida com sucesso.");
		return "redirect:/admin/crud-locadoras";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		System.out.println("Passei por /locadoras/editar/id especifico");
		model.addAttribute("locadora", service.buscarPorId(id));
		return "locadora/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
		
		System.out.println("Passei por /locadoras/editar");

		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			return "locadora/cadastro";
		}

		System.out.println(locadora.getPassword());
		
		service.salvar(locadora);
		attr.addFlashAttribute("sucess", "Locadora editada com sucesso.");
		return "redirect:/locadoras/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model, RedirectAttributes attr) {
		System.out.println("Passei por /locadoras/excluir");
		service.excluir(id);
		attr.addFlashAttribute("sucess", "Locadora excluída com sucesso.");
		return "redirect:/locadoras/listar";
	}

	@GetMapping("/cidades")
    @ResponseBody
    public String getCidades() {
        try {
            Resource resource = new ClassPathResource("/cidades/cidades.txt");
            byte[] content = Files.readAllBytes(Paths.get(resource.getURI()));
            return new String(content, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}