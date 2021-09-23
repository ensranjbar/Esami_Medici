  
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
	
	public Optional<Esame> findByCodice(String keyword);

	@Query(value="select * from esame where paziente_id=?1",nativeQuery=true)
    public List<Esame> findByUser(Long id);
	
	@Query(value="select * from esame where medico_id=?1", nativeQuery=true)
	public List<Esame> findByMedico(Long id);
	
	@Query(value="select id from esame where codice=?1", nativeQuery = true)
	public Long search(String keyword);
}
