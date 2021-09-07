  
package it.uniroma3.siw.spring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.User;

public interface EsameRepository extends CrudRepository<Esame, Long> {
	
	public Optional<Esame> findByPaziente(User paziente);
	
	public Optional<Esame> findByMedico(Medico medico);

	
}
