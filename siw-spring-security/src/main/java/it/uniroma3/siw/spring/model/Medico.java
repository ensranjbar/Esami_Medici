package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;

@Data
@Entity
public class Medico {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String cognome;
	
	@Column(nullable = false)
	private String Specializzazione;
	
	@OneToMany(mappedBy="medico", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
	private List<Esame> esami;

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", Specializzazione=" + Specializzazione
				+ "]";
	}

	

}
