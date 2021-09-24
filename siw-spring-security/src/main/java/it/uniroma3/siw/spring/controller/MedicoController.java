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
import it.uniroma3.siw.spring.controller.validator.MedicoValidator;

import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.repository.MedicoRepository;
import it.uniroma3.siw.spring.service.EsameService;
import it.uniroma3.siw.spring.service.MedicoService;

@Controller
public class MedicoController {

	@Autowired
	private MedicoService medicoService;
	@Autowired
	private EsameService esameService;

	@Autowired
	private  MedicoRepository medicoRepository;
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
		model.addAttribute("esami", this.esameService.listaPMedico(id));
	//model.addAttribute("role", this.medicoService.getCredentialsService().getRoleAuthenticated());
		return "medico";
	}

	@RequestMapping(value = "/medico", method = RequestMethod.GET)
	public String getMedici(Model model) {
		model.addAttribute("medici", this.medicoService.tutti());
		//model.addAttribute("role", this.medicoService.getCredentialsService().getRoleAuthenticated());
		return "medici";
	}

	 @PostMapping("/admin/mediciSave")
	    public String saveMedico(@ModelAttribute Medico medico,
	 Model model ,      @RequestParam("image") MultipartFile multipartFile, BindingResult bindingResult) throws IOException {
	        if(!bindingResult.hasErrors()) { 
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
	        medico.setPhotos(fileName);
	         
	       Medico savedMedico = medicoRepository.save(medico);
	 
	        String uploadDir = "medico-photos/" + savedMedico.getId();
	 
	        FileUploadApplication.saveFile(uploadDir, fileName, multipartFile);
	        model.addAttribute("medici", this.medicoService.tutti());
	        
	        return "medici";}
	        
	        else
	        	return "medicoForm";
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
	
	 @RequestMapping(value="/admin/medico/{id}", method= RequestMethod.GET)
	    public String deleteMedico(@PathVariable("id")Long id, Model model) {
	    		this.medicoService.deletedMedico(id);
	    		model.addAttribute("medici",this.medicoService.tutti());
	        	model.addAttribute("role", this.medicoService.getCredentialsService().getRoleAuthenticated());

	    		return "medici";	
	    }
  
}
