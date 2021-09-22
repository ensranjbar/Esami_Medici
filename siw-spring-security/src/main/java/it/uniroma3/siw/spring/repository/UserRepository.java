package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value="select u.id, u.nome, u.cognome from users u join credentials cr ON u.id = cr.user_id where cr.role = 'DEFAULT'",nativeQuery=true)
    public List<User> findPazienti();

}