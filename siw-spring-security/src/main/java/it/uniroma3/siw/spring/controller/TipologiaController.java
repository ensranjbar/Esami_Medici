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

import it.uniroma3.siw.spring.controller.validator.TipologiaValidator;
import it.uniroma3.siw.spring.model.Prerequisito;
import it.uniroma3.siw.spring.model.Tipologia;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.PrerequisitoService;
import it.uniroma3.siw.spring.service.TipologiaService;

@Controller
public class TipologiaController {

	@Autowired
	private TipologiaService tipologiaService;

	@Autowired
	private TipologiaValidator tipologiaValidator;
	@Autowired
	private PrerequisitoService prerequisitoService;

	@RequestMapping(value = "/admin/tipologia", method = RequestMethod.GET)
	public String addTipologia(Model model) {
		model.addAttribute("tipologia", new Tipologia());
		return "tipologiaForm";
	}

	@RequestMapping(value = "/tipologia/{id}", method = RequestMethod.GET)
	public String getTipologia(@PathVariable("id") Long id, Model model) {
		model.addAttribute("tipologia", this.tipologiaService.tipologiaPerId(id));
		return "tipologia";
	}

	@RequestMapping(value = "/tipologia", method = RequestMethod.GET)
	public String getTipologie(Model model) {
		model.addAttribute("tipologie", this.tipologiaService.tutti());
		return "tipologie";
	}

	@RequestMapping(value = "/admin/tipologia", method = RequestMethod.POST)
	public String addTipologia(@ModelAttribute("tipologia") Tipologia tipologia, Model model,
			BindingResult bindingResult) {
		this.tipologiaValidator.validate(tipologia, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.tipologiaService.inserisci(tipologia);
			model.addAttribute("tipologie", this.tipologiaService.tutti());
			return "tipologie";
		}
		return "tipologiaForm";
	}

	@RequestMapping(value= "/admin/deleteTipologia/{id}", method = RequestMethod.POST)
    public String deleteTipologia(@PathVariable("id") Long id) {
    	this.tipologiaService.deleteTipologiaById(id);
    	return "admin/home";
    }
}

/*
 * INSERIMENTO
 * Prerequisito-----------------------------------------------------------------
 * -----------------------------------------------------------------------------
 * -------------------------------------
 * 
 * @RequestMapping(value="/tipologie/{tipologia.id}/addPrerequisito", method =
 * RequestMethod.GET) public String addPrerequisito(Model model) {
 * model.addAttribute("prerequisito", new Prerequisito()); return
 * "prerequisitoForm"; }
 * 
 * @RequestMapping(value = "/tipologie/{tipologia.id}/getPrerequisito/{id}",
 * method = RequestMethod.GET) public String getPrerequisito(@PathVariable("id")
 * Long id, Model model) { model.addAttribute("prerequisito",
 * this.prerequisitoService.prerequisitoPerId(id)); return "prerequisito"; }
 * 
 * @RequestMapping(value = "/tipologie/{tipologia.id}/getPrerequisiti", method =
 * RequestMethod.GET) public String getPrerequisiti(Model model) {
 * model.addAttribute("prerequisiti", this.prerequisitoService.tutti()); return
 * "prerequisiti"; }
 * 
 * 
 * 
 * @RequestMapping(value=
 * "/admin/tipologie/{tipologia.id}/deletePrerequisito/{id}", method =
 * RequestMethod.POST) public String deletePrerequisito(@PathVariable("id") Long
 * id) { this.prerequisitoService.deletePrerequisitoById(id); return
 * "prerequisiti"; }
 * 
 * }
 */