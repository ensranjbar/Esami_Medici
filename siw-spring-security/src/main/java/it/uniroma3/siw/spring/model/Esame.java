package it.uniroma3.siw.spring.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Esame {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//data orarioPrenotazione
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // HH:mm:ss
	private LocalDate dataPrenotazione;

	//dataOrarioEsame
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd") // HH:mm:ss
	private LocalDate dataEsame;//string
	

	@OneToMany(mappedBy="esame",cascade=CascadeType.ALL)
	private List <Risultato> risultati;
	
	@ManyToOne
	private User paziente;
	
	@ManyToOne
	private Tipologia tipologia;
	
	@ManyToOne
	private Medico medico;

	@Override
	public String toString() {
		return "Esame [id=" + id + ", dataPrenotazione=" + dataPrenotazione + ", dataEsame=" + dataEsame
				+ ", risultati=" + risultati + ", paziente=" + paziente + ", medico=" + medico + "]";
	}
	

	

}
