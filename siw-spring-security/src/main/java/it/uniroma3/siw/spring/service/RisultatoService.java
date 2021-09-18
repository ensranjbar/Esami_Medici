package it.uniroma3.siw.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Risultato;
import it.uniroma3.siw.spring.repository.RisultatoRepository;

@Service
public class RisultatoService {
	

	@Autowired
	private RisultatoRepository risultatoRepository;
	@Autowired
	private CredentialsService credentialsService;
	
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
	public boolean deletedRisulatato(Long id) {
		try {
			this.risultatoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
