package it.uniroma3.siw.spring.model;

import java.beans.Transient;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Tipologia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String descrizione;

	@Column(nullable = false)
	private float costo;
	/*
	 * @Column(nullable = true, length = 64) private String photos;
	 * 
	 * @Transient public String getPhotosImagePath() { if (photos == null || id ==
	 * null) return null;
	 * 
	 * return "/tipologia-photos/" + id + "/" + photos; }
	 */

	@OneToMany(mappedBy = "tipologia", cascade =  CascadeType.REMOVE)

	private List<Esame> esami;

	/*
	 * @OneToMany(mappedBy="tipologia",cascade=CascadeType.ALL) private List
	 * <Prerequisito> prerequisiti;
	 */

	@ManyToMany
	private List<Prerequisito> prerequisiti;

	@Override
	public String toString() {
		return "Tipologia [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", costo=" + costo
				+ ", prerequisito=" + prerequisiti + "]";
	}

}
