package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Risultato;
import it.uniroma3.siw.spring.service.RisultatoService;

@Component
public class RisultatoValidator implements Validator {
	@Autowired
	private RisultatoService risultatoService;
	
    private static final Logger logger = LoggerFactory.getLogger(RisultatoValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "risultatoDescrizione", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.risultatoService.alreadyExists((Risultato)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Risultato.class.equals(aClass);
	}

	

}
