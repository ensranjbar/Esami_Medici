package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.PrerequisitoValidator;
import it.uniroma3.siw.spring.controller.validator.TipologiaValidator;
import it.uniroma3.siw.spring.model.Prerequisito;
import it.uniroma3.siw.spring.model.Tipologia;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.PrerequisitoService;
import it.uniroma3.siw.spring.service.TipologiaService;

@Controller
public class PrerequisitoController {

	@Autowired
	private PrerequisitoValidator prerequisitoValidator;
	@Autowired
	private PrerequisitoService prerequisitoService;
	

	@RequestMapping(value = "/admin/prerequisito", method = RequestMethod.GET)
	public String addPrerequisito(Model model) {
		model.addAttribute("prerequisito", new Prerequisito());
		return "prerequisitoForm";
	}

	@RequestMapping(value = "/prerequisito/{id}", method = RequestMethod.GET)
	public String getPrerequisito(@PathVariable("id") Long id, Model model) {
		model.addAttribute("prerequisito", this.prerequisitoService.prerequisitoPerId(id));
		//model.addAttribute("role", this.prerequisitoService.getCredentialsService().getRoleAuthenticated());
		return "prerequisito";
	}

	@RequestMapping(value = "/prerequisito", method = RequestMethod.GET)
	public String getPrerequisiti(Model model) {
		model.addAttribute("prerequisiti", this.prerequisitoService.tutti());
		//model.addAttribute("role", this.prerequisitoService.getCredentialsService().getRoleAuthenticated());
		return "prerequisiti";
	}

	@RequestMapping(value = "/admin/prerequisito", method = RequestMethod.POST)
	public String addPrerequisito(@ModelAttribute("prerequisito") Prerequisito prerequisito, Model model,
			BindingResult bindingResult) {
		this.prerequisitoValidator.validate(prerequisito, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.prerequisitoService.inserisci(prerequisito);
			model.addAttribute("prerequisiti", this.prerequisitoService.tutti());
			return "prerequisiti";
		}
		return "prerequisitoForm";
	}

	 @RequestMapping(value="/admin/prerequisito/{id}", method= RequestMethod.GET)
	    public String deletePrerequisito(@PathVariable("id")Long id, Model model) {
	    		this.prerequisitoService.deletedPrerequisito(id);
	    		model.addAttribute("prerequisiti",this.prerequisitoService.tutti());
	        	model.addAttribute("role", this.prerequisitoService.getCredentialsService().getRoleAuthenticated());

	    		return "prerequisiti";	
	    }
}
