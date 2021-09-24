package it.uniroma3.siw.spring.controller;

import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.FileUploadApplication;
import it.uniroma3.siw.spring.controller.validator.PrerequisitoValidator;
import it.uniroma3.siw.spring.controller.validator.RisultatoValidator;
import it.uniroma3.siw.spring.controller.validator.TipologiaValidator;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.Prerequisito;
import it.uniroma3.siw.spring.model.Risultato;
import it.uniroma3.siw.spring.model.Tipologia;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.RisultatoRepository;
import it.uniroma3.siw.spring.service.PrerequisitoService;
import it.uniroma3.siw.spring.service.RisultatoService;
import it.uniroma3.siw.spring.service.TipologiaService;

@Controller
public class RisultatoController {

	@Autowired
	private RisultatoValidator risultatoValidator;
	@Autowired
	private RisultatoService risultatoService;
	@Autowired
	private RisultatoRepository risultatoRepository;
	
	

	@RequestMapping(value = "/admin/risultato", method = RequestMethod.GET)
	public String addRisultato(Model model) {
		model.addAttribute("risultato", new Risultato());
		model.addAttribute("pazienti",this.risultatoService.getPazienteService().tutti());
		model.addAttribute("esami",this.risultatoService.getEsameService().tutti());
		
		return "risultatoForm";
	}

	@RequestMapping(value = "/risultato/{id}", method = RequestMethod.GET)
	public String getRisultato(@PathVariable("id") Long id, Model model) {
		model.addAttribute("risultato", this.risultatoService.risultatoPerId(id));
		//model.addAttribute("role", this.prerequisitoService.getCredentialsService().getRoleAuthenticated());
		return "risultato";
	}

	@RequestMapping(value = "/risultato", method = RequestMethod.GET)
	public String getRisultati(Model model) {
		model.addAttribute("risultati", this.risultatoService.tutti());
		model.addAttribute("role", this.risultatoService.getCredentialsService().getRoleAuthenticated());
		return "risultati";
	}

	 @PostMapping("/risultatiSave")
	    public String saveRisultato(@ModelAttribute Risultato risultato,
	 Model model ,      @RequestParam("documentFile") MultipartFile multipartFile, BindingResult bindingResult) throws IOException {
	        if(!bindingResult.hasErrors()) { 
	        String documentFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        risultato.setDocumenti(documentFileName);
	         
	       Risultato savedRisultato = risultatoRepository.save(risultato);
	        String uploadDir = "risultato-documenti/" + savedRisultato.getId();
	 
	        FileUploadApplication.saveFile(uploadDir,documentFileName, multipartFile);
	        model.addAttribute("risultati", this.risultatoService.tutti());
	        
	        return "risultati";}
	        
	        else
	        	return "risultatoForm";
	    }
	@RequestMapping(value = "/admin/risultato", method = RequestMethod.POST)
	public String addRisultato(@ModelAttribute("risultato") Risultato risultato, Model model,
			BindingResult bindingResult) {
		this.risultatoValidator.validate(risultato, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.risultatoService.inserisci(risultato);
			model.addAttribute("risultati", this.risultatoService.tutti());
			return "risultati";
		}
		return "risultatoForm";
	}

	 @RequestMapping(value="/admin/risultato/{id}", method= RequestMethod.GET)
	    public String deleterisultato(@PathVariable("id")Long id, Model model) {
		 //	logger.debug("inzio eliminazione");
	    		this.risultatoService.deletedRisultato(id);
	    	//	logger.debug("risultato cancellato");
	    		model.addAttribute("risultati",this.risultatoService.tutti());
	        	model.addAttribute("role", this.risultatoService.getCredentialsService().getRoleAuthenticated());

	    		return "risultati";	
	    }
}
