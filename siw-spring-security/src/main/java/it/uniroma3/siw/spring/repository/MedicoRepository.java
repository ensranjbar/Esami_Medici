package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Medico;

public interface MedicoRepository extends CrudRepository<Medico, Long> {

	public List<Medico> findByNome(String nome);

	public List<Medico> findByNomeAndCognome(String nome, String cognome);

	public List<Medico> findByNomeOrCognome(String nome, String cognome);

}