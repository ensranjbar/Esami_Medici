package it.uniroma3.siw.spring.model;

import java.beans.Transient;
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
	@Column(nullable = true, length = 64)
	private String photos;

	@Transient
	public String getPhotosImagePath() {
		if (photos == null || id == null)
			return null;

		return "/medico-photos/" + id + "/" + photos;
	}

	@OneToMany(mappedBy = "medico", cascade = CascadeType.REMOVE)
	private List<Esame> esami;

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", Specializzazione=" + Specializzazione
				+ "]";
	}

}
