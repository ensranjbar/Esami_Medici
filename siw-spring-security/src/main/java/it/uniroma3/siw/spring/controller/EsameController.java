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

import it.uniroma3.siw.spring.controller.validator.EsameValidator;
import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.EsameService;

@Controller
public class EsameController {
	
	@Autowired
	private EsameService esameService;
	
    @Autowired
    private EsameValidator esameValidator;
        
    @RequestMapping(value="/admin/esame", method = RequestMethod.GET)
    public String addExam(Model model) {
    	model.addAttribute("esame", new Esame());
    	model.addAttribute("paziente", this.esameService.getUserService().tutti());
    	model.addAttribute("medico", this.esameService.getMedicoService().tutti());
    	model.addAttribute("tipologia", this.esameService.getTipologiaService().tutti());
        return "esameForm";
    }

    @RequestMapping(value = "/esame/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.esameById(id));
    	model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());

    	return "esame";
    }

    @RequestMapping(value = "/admin/modEsame/{id}", method = RequestMethod.GET)
    public String modEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.esameById(id));
    	model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());

    	return "esameFormMod";
    }
    @RequestMapping(value ="/admin/esameUpdate")
    public String updateEsame(@ModelAttribute("esame")Esame esame,
    		Model model, BindingResult bindingResult){
    	this.esameService.inserisci(esame);
    	
    	return "esami";
    	

}
  /*  @RequestMapping(value = "/esame", method = RequestMethod.GET)
    public String getEsami(Model model) {
    		model.addAttribute("esami", this.esameService.tutti());
    		return "esami";
    }*/
    @RequestMapping(value = "/esame", method = RequestMethod.GET)
    public String getEsamiByPaziente(@ModelAttribute("paziente") User paziente,Model model) {
    		model.addAttribute("esami", this.esameService.esameByPaziente(paziente));
    		return "esami";
    }
    
    @RequestMapping(value = "/admin/esame", method = RequestMethod.POST)
    public String newEsame(@ModelAttribute("esame") Esame esame, 
    									Model model, BindingResult bindingResult) {
    	this.esameValidator.validate(esame, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.esameService.inserisci(esame);
            model.addAttribute("esami", this.esameService.tutti());
            return "esami";
        }
        return "esameForm";
    }
   
    @RequestMapping(value= "/admin/deleteEsame/{id}", method = RequestMethod.POST)
    public String deleteEsame(@PathVariable("id") Long id) {
    	this.esameService.deleteEsameById(id);
    	return "esami";
    }
  

}