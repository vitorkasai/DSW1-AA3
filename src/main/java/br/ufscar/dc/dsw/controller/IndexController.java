package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/index")
public class IndexController {
	@Autowired
	private ILocadoraService serviceLocadora;
	@GetMapping("/listagem")
	public String listarLocadoras(ModelMap model, Locadora locadora) {
		System.out.println("Passei por /index/listagem");
		model.addAttribute("locadoras",serviceLocadora.buscarTodos());
		return "locadora/locadoras";
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
