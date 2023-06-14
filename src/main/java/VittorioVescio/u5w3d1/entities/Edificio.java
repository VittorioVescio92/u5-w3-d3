package VittorioVescio.u5w3d1.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "edifici")
@JsonIgnoreProperties({ "safetyCode" })
public class Edificio {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;
	private String indirizzo;
	private String safetyCode;

	@ManyToOne
	private Citta citta;

	public Edificio(String nome, String indirizzo, Citta citta, String safetyCode) {
		super();
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.citta = citta;
		this.safetyCode = safetyCode;
	}

}
