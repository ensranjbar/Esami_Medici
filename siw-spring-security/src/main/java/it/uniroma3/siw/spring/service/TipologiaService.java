package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Tipologia;

import it.uniroma3.siw.spring.repository.TipologiaRepository;

@Service
public class TipologiaService {

	@Autowired
	private TipologiaRepository tipologiaRepository;
	@Autowired
	private CredentialsService credentialsService;

	@Transactional
	public Tipologia inserisci(Tipologia tipologia) {
		return tipologiaRepository.save(tipologia);
	}

	@Transactional
	public List<Tipologia> tutti() {
		return (List<Tipologia>) tipologiaRepository.findAll();
	}

	@Transactional
	public Tipologia tipologiaPerId(Long id) {
		Optional<Tipologia> optional = tipologiaRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Transactional
	public boolean alreadyExists(Tipologia tipologia) {
		List<Tipologia> tipologie = this.tipologiaRepository.findByNome(tipologia.getNome());
		if (tipologie.size() > 0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}
}
