package it.uniroma3.siw.spring.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.repository.MedicoRepository;


@Service
public class MedicoService {
	
	@Autowired
	private MedicoRepository medicoRepository; 
	@Autowired
	private CredentialsService credentialsService;
	
	@Transactional
	public Medico inserisci(Medico medico) {
		return medicoRepository.save(medico);
	}

	@Transactional
	public List<Medico> tutti() {
		return (List<Medico>) medicoRepository.findAll();
	}

	@Transactional
	public Medico medicoPerId(Long id) {
		Optional<Medico> optional = medicoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Medico medico) {
		List<Medico> medici = this.medicoRepository.findByNomeAndCognome(medico.getNome(),medico.getCognome());
		if (medici.size() > 0)
			return true;
		else 
			return false;
	}
	
	@Transactional
	public CredentialsService getCredentialsService() {
		return credentialsService;
	}

	public void deleteMedicoById(Long id) {
		medicoRepository.deleteById(id);
	}
	@Transactional
	public boolean deletedMedico(Long id) {
		try {
			this.medicoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}


}
