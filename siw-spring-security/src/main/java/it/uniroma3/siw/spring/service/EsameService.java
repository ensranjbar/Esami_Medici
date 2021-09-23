package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.EsameRepository;

@Service
public class EsameService {
	@Autowired
	private EsameRepository esameRepository; 
	@Autowired
	private UserService userService; 
	@Autowired
	private MedicoService medicoService; 
	@Autowired
	private TipologiaService tipologiaService; 
	@Autowired
	private CredentialsService credentialsService;
	
	
	@Transactional
	public Esame inserisci(Esame esame) {
		return esameRepository.save(esame);
	}

	@Transactional
	public List<Esame> tutti() {
		return (List<Esame>) esameRepository.findAll();
	}
	
	@Transactional
	public Long ricerca(String keyword) {
		return esameRepository.search(keyword);
	}
	
	@Transactional
	public Esame esameById(Long id) {
		Optional<Esame> optional = esameRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public Esame esameByPaziente(User paziente) {
		Optional<Esame> optional = esameRepository.findByPaziente(paziente);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	@Transactional
	public Esame esameByMedico(Medico medico) {
		Optional<Esame> optional = esameRepository.findByMedico(medico);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}
	
	@Transactional
	public Esame esameByCodice(String keyword) {
		Optional<Esame> optional = esameRepository.findByCodice(keyword);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Esame esame) {
		Optional<Esame> esami = this.esameRepository.findByPaziente(esame.getPaziente());
		if (esami.isPresent())
			return true;
		else 
			return false;
	}
	
	@Transactional	
	public UserService getUserService() {
		return this.userService;
	}

	@Transactional
	public MedicoService getMedicoService() {
		return this.medicoService;
	}

	@Transactional
	public TipologiaService getTipologiaService() {
		return this.tipologiaService;
	}
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	} 

	@Transactional
	public void deleteEsameById(Long id) {
		esameRepository.deleteById(id);
	}
	@Transactional
	public boolean deletedEsame(Long id) {
		try {
			this.esameRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public List<Esame> listaPUtente(Long id) {
		return (List<Esame>) esameRepository.findByUser(id);
	}
	
	public List<Esame> listaPMedico(Long id) {
		return (List<Esame>) esameRepository.findByMedico(id);
	}

}
