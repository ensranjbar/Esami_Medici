package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prerequisiti;

public interface PrerequisitiRepository extends CrudRepository<Prerequisiti, Long> {

	public List<Prerequisiti> findByNome(String nome);

	public List<Prerequisiti> findByNomeAndValore(String nome, String valore);

	public List<Prerequisiti> findByNomeOrValore(String nome, String valore);

}