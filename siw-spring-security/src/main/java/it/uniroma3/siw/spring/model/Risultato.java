package it.uniroma3.siw.spring.model;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Risultato {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Column(nullable = false)
	private String nome;


	@Column(nullable = false)
	private String Valore;
	
	@Column(nullable = true)
	private String documenti;


	@OneToOne
	private Esame esame;


	@Override
	public String toString() {
		return "Risultato [id=" + id + ", nome=" + nome + ", Valore=" + Valore + "]";
	}
	

}
