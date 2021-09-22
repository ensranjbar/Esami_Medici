  
package it.uniroma3.siw.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.spring.model.Esame;
import it.uniroma3.siw.spring.model.Medico;
import it.uniroma3.siw.spring.model.User;

public interface EsameRepository extends CrudRepository<Esame, Long> {
	
	public Optional<Esame> findByPaziente(User paziente);
	
	public Optional<Esame> findByMedico(Medico medico);

	@Query(value="select * from esame where paziente_id=?1",nativeQuery=true)
    public List<Esame> findByUser(Long id);
}
