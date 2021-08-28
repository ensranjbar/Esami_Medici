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

import it.uniroma3.siw.spring.controller.validator.MedicoValidator;

import it.uniroma3.siw.spring.model.Medico;

import it.uniroma3.siw.spring.service.MedicoService;

@Controller
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private MedicoValidator medicoValidator;

	@RequestMapping(value = "/admin/medico", method = RequestMethod.GET)
	public String addMedico(Model model) {
		model.addAttribute("medico", new Medico());
		return "medicoForm";
	}

	@RequestMapping(value = "/medico/{id}", method = RequestMethod.GET)
	public String getMedico(@PathVariable("id") Long id, Model model) {
		model.addAttribute("medico", this.medicoService.medicoPerId(id));
		return "medico";
	}

	@RequestMapping(value = "/medico", method = RequestMethod.GET)
	public String getMedici(Model model) {
		model.addAttribute("medici", this.medicoService.tutti());
		return "medici";
	}

	@RequestMapping(value = "/admin/medico", method = RequestMethod.POST)
	public String addMedico(@ModelAttribute("medico") Medico medico, Model model, BindingResult bindingResult) {
		this.medicoValidator.validate(medico, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.medicoService.inserisci(medico);
			model.addAttribute("medici", this.medicoService.tutti());
			return "medici";
		}
		return "medicoForm";
	}
}
