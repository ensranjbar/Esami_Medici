package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Risultato;

public interface RisultatoRepository extends CrudRepository<Risultato, Long>{

	List<Risultato> findByNome(String nome);
	

}