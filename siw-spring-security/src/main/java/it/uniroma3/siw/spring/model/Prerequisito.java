package it.uniroma3.siw.spring.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


import lombok.Data;

@Data
@Entity
public class Prerequisito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String valore;

	/* @ManyToOne
	private Tipologia tipologia; */

	@ManyToMany(mappedBy = "prerequisiti")
	private List<Tipologia> tipologie;

	@Override
	public String toString() {
		return "Prerequisito [id=" + id + ", nome=" + nome + ", valore=" + valore + "]";
	}

	
}
