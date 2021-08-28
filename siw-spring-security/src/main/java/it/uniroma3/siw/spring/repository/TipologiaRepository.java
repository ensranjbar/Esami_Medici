package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Tipologia;

public interface TipologiaRepository  extends CrudRepository<Tipologia, Long> {

	public List<Tipologia> findByNome(String nome);

	public List<Tipologia> findByNomeAndCosto(String nome, String costo);

	public List<Tipologia> findByNomeOrCosto(String nome, String costo);

}