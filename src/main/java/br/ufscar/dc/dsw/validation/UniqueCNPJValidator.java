package br.ufscar.dc.dsw.validation;
import br.ufscar.dc.dsw.dao.ILocadoraDAO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

	@Autowired
	private ILocadoraDAO dao;

	@Override
	public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
		if (dao != null) {
			Locadora locadora = dao.findByCNPJ(CNPJ);
			return locadora == null;
		} else {
            // Não necessidade de validação
			// Durante a execução da classe LivrariaMvcApplication
			// não há injeção de dependência. 
			return true;
		}
	}
}
