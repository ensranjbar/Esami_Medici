package it.uniroma3.siw.spring.controller;

import java.time.LocalDate;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.EsameValidator;
import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.EsameService;

@Controller
public class EsameController {
	
	@Autowired
	private EsameService esameService;
	@Autowired
	private CredentialsService credentialsService;
	
    @Autowired
    private EsameValidator esameValidator;
        
    @RequestMapping(value="/admin/esame", method = RequestMethod.GET)
    public String addEsame(Model model) {
    	model.addAttribute("esame", new Esame());
    	model.addAttribute("pazienti", this.esameService.getUserService().listaPazienti());
    	model.addAttribute("medici", this.esameService.getMedicoService().tutti());
    	model.addAttribute("tipologie", this.esameService.getTipologiaService().tutti());
        return "esameForm";
    }

    @RequestMapping(value = "/esame/{id}", method = RequestMethod.GET)
    public String getEsame(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("esame", this.esameService.esameById(id));
    	//model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());

    	return "esame";
    }
    
    /* @RequestMapping(value = "/esame+?1", method = RequestMethod.GET)
    public String cercaEsame(Model model, @Param("keyword") String keyword) {
    	Long id = this.esameService.ricerca(keyword);
        model.addAttribute("id", id);
        model.addAttribute("keyword", keyword);
         
        return "redirect:/esame";
    } */
    
    @RequestMapping(value = "/esame/{keyword}", method = RequestMethod.GET)
    public String getEsameCodice(@Param("keyword") String keyword, Model model) {
    	if(this.esameService.esameByCodice(keyword) != null) {
    	  model.addAttribute("esame", this.esameService.esameByCodice(keyword));
    	  model.addAttribute("keyword", keyword);
    	//model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());
    	  return "esame";
    	}
    	else
    		keyword = "nessun esame corrispondente";
    		model.addAttribute("keyword", keyword);
    		return "admin/home";
    }
   
    @RequestMapping(value = "/esame", method = RequestMethod.GET)
    public String mostraPrenotazioniDellUtente(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = this.credentialsService.getCredentials(userDetails.getUsername());
        User cliente = credentials.getUser();
        model.addAttribute("esami", this.esameService.listaPUtente(cliente.getId()));
    	return "esami";
    }

    
    @RequestMapping(value = "/admin/esame", method = RequestMethod.POST)
    public String newEsame(@ModelAttribute("esame") Esame esame, 
    									Model model, BindingResult bindingResult) {
    	
        if (!bindingResult.hasErrors()) {
        	esame.setDataPrenotazione(LocalDate.now());      
        	this.esameService.inserisci(esame);
            model.addAttribute("esami", this.esameService.tutti());
            return "esami";
        }
        return "esameForm";
    }
   
	 @RequestMapping(value="/admin/esame/{id}", method= RequestMethod.GET)
	    public String deleteEsame(@PathVariable("id")Long id, Model model) {
	    		this.esameService.deletedEsame(id);
	    		model.addAttribute("esami",this.esameService.tutti());
	        	model.addAttribute("role", this.esameService.getCredentialsService().getRoleAuthenticated());

	    		return "esami";	
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

  

}