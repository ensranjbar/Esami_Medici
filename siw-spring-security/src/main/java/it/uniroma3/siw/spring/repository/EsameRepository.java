package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.User;

public interface EsameRepository {
	
	public Optional<Esame> findByPaziente(User paziente);
	
	public Optional<Esame> findByMedico(Medico medico);

	public List<Esame> findAll();

	public Esame save(Esame esame);

	public Optional<Esame> findById(Long id);

	
}
