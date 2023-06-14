package VittorioVescio.u5w3d1.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "postazioni")
public class Postazione {

	@Id
	@GeneratedValue
	private UUID id;

	private String descrizione;
	private Integer numeroMassimoOccupanti;

	@Enumerated(EnumType.STRING)
	private TipoPostazione tipo;

	@ManyToOne
	private Edificio edificio;

	public Postazione(String descrizione, Integer numeroMassimoOccupanti, TipoPostazione tipo, Edificio edificio) {
		super();
		this.descrizione = descrizione;
		this.numeroMassimoOccupanti = numeroMassimoOccupanti;
		this.tipo = tipo;
		this.edificio = edificio;
	}

}
