package it.uniroma3.siw.spring.controller;

import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import it.uniroma3.siw.FileUploadApplication;
import it.uniroma3.siw.spring.controller.validator.TipologiaValidator;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.Prerequisito;
import it.uniroma3.siw.spring.model.Tipologia;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.TipologiaRepository;
import it.uniroma3.siw.spring.service.PrerequisitoService;
import it.uniroma3.siw.spring.service.TipologiaService;

@Controller
public class TipologiaController {

	@Autowired
	private TipologiaService tipologiaService;

	@Autowired
	private TipologiaValidator tipologiaValidator;
	@Autowired
	private TipologiaRepository tipologiaRepository;

	@RequestMapping(value = "/admin/tipologia", method = RequestMethod.GET)
	public String addTipologia(Model model) {
		model.addAttribute("tipologia", new Tipologia());
		model.addAttribute("prerequisiti",this.tipologiaService.getPrerequisitoService().tutti());
		return "tipologiaForm";
	}

	@RequestMapping(value = "/tipologia/{id}", method = RequestMethod.GET)
	public String getTipologia(@PathVariable("id") Long id, Model model) {
		model.addAttribute("tipologia", this.tipologiaService.tipologiaPerId(id));
		model.addAttribute("prerequisiti",this.tipologiaService.getPrerequisitoService().getPrerequisitiTipologia(id));
	//	model.addAttribute("role", this.tipologiaService.getCredentialsService().getRoleAuthenticated());
		return "tipologia";
	}

	@RequestMapping(value = "/tipologia", method = RequestMethod.GET)
	public String getTipologie(Model model) {
		model.addAttribute("tipologie", this.tipologiaService.tutti());
		//model.addAttribute("role", this.tipologiaService.getCredentialsService().getRoleAuthenticated());
		return "tipologie";
	}
	
	/*
	 * @PostMapping("/admin/tipologieSave") public String
	 * saveTipologia(@ModelAttribute Tipologia tipologia, Model model
	 * , @RequestParam("image") MultipartFile multipartFile, BindingResult
	 * bindingResult) throws IOException { if(!bindingResult.hasErrors()) { String
	 * fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	 * tipologia.setPhotos(fileName);
	 * 
	 * Tipologia savedTipologia = tipologiaRepository.save(tipologia);
	 * 
	 * String uploadDir = "tipologia-photos/" + savedTipologia.getId();
	 * 
	 * FileUploadApplication.saveFile(uploadDir, fileName, multipartFile);
	 * model.addAttribute("tipologie", this.tipologiaService.tutti());
	 * 
	 * return "tipologie";}
	 * 
	 * else return "tipologiaForm"; }
	 */

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

	 @RequestMapping(value="/admin/tipologia/{id}", method= RequestMethod.GET)
	    public String deleteTipologia(@PathVariable("id")Long id, Model model) {
	 
	    		this.tipologiaService.deleteTipologiaById(id);
	    		model.addAttribute("tipologie",this.tipologiaService.tutti());
	        	model.addAttribute("role", this.tipologiaService.getCredentialsService().getRoleAuthenticated());

	    		return "tipologie";	
	    }
}
