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
public class Tipologia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String descrizione;
	
	@Column(nullable=false)
	private float costo; 
	
	
	@OneToMany(mappedBy="tipologia",cascade=CascadeType.ALL)
	private List<Esame> esami;
	
	@OneToMany(mappedBy="tipologia",cascade=CascadeType.ALL)
	private List <Prerequisito> prerequisito;
	
}
