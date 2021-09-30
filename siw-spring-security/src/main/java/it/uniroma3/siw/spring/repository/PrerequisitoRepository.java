package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Prerequisito;

public interface PrerequisitoRepository extends CrudRepository<Prerequisito, Long> {

	public List<Prerequisito> findByNome(String nome);

	public List<Prerequisito> findByNomeAndValore(String nome, String valore);

	// public List<Prerequisito> findPrerequisitiByCorso(Long id);
	
	@Query(value="select * from tipologia_prerequisiti tp join prerequisito p on tp.prerequisiti_id = p.id where tipologie_id=?1", nativeQuery = true)
	public List<Prerequisito> findPrerequisitiByTipologia(Long id);
}
