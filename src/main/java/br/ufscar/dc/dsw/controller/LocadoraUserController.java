package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
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

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.LocadoraDetails;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/locadora")
public class LocadoraUserController {

	@Autowired
	private ILocacaoService serviceLocacao;

	private Locadora getLocadora() {
		LocadoraDetails locadoraDetails = (LocadoraDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return locadoraDetails.getLocadora();
	}

	@GetMapping("/listar")
	public String listarLocacoes(Locadora locadora, ModelMap model) {
		locadora = getLocadora();
		model.addAttribute("locacoes",serviceLocacao.buscarTodosLocadora(locadora));
		return "locadora/listagemLocacoes";
	}
}
