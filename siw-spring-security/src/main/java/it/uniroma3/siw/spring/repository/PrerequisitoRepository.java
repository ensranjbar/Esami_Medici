package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prerequisito;

public interface PrerequisitoRepository extends CrudRepository<Prerequisito, Long> {

	public List<Prerequisito> findByNome(String nome);

	public List<Prerequisito> findByNomeAndValore(String nome, String valore);

}