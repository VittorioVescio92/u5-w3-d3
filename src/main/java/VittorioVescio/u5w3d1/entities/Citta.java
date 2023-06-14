package VittorioVescio.u5w3d1.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "citta")
public class Citta {

	@Id
	@GeneratedValue
	private UUID id;
	private String nome;

	public Citta(String nome) {
		super();
		this.nome = nome;
	}

}
