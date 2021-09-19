package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Risultato;
import it.uniroma3.siw.spring.model.Tipologia;
import it.uniroma3.siw.spring.repository.RisultatoRepository;

@Service
public class RisultatoService {
	

	@Autowired
	private RisultatoRepository risultatoRepository;
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private EsameService esameService;
	@Autowired
	private UserService pazienteService;
	
	@Transactional
	public Risultato inserisci(Risultato risultato) {
		return risultatoRepository.save(risultato);
	}
	
	@Transactional
	public List<Risultato> tutti() {
		return (List<Risultato>) risultatoRepository.findAll();
	}
	
	@Transactional
	public boolean alreadyExists(Risultato risultato) {
		List<Risultato> risultati = this.risultatoRepository.findByNome(risultato.getNome());
		if (risultati.size() > 0)
			return true;
		else 
			return false;
	}
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	@Transactional
	public boolean deletedRisultato(Long id) {
		try {
			this.risultatoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	@Transactional
	public Risultato risultatoPerId(Long id) {
		Optional<Risultato> optional = risultatoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}
	
	public EsameService getEsameService() {
		return this.esameService;
	}
	public UserService getPazienteService() {
		return this.pazienteService;
	}
}
