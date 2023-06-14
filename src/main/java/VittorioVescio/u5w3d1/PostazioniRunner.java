package VittorioVescio.u5w3d1;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import VittorioVescio.u5w3d1.entities.Edificio;
import VittorioVescio.u5w3d1.entities.Postazione;
import VittorioVescio.u5w3d1.entities.TipoPostazione;
import VittorioVescio.u5w3d1.repositories.EdificiRepository;
import VittorioVescio.u5w3d1.repositories.PostazioniRepository;

@Component
public class PostazioniRunner implements CommandLineRunner {
	@Autowired
	PostazioniRepository postazioniRepo;
	@Autowired
	EdificiRepository edificiRepo;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		List<Postazione> postazioniDB = postazioniRepo.findAll();
		List<Edificio> edificiDB = edificiRepo.findAll();

		if (postazioniDB.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String descrizione = faker.lorem().sentence();
					int numMassimoOccupanti = faker.number().numberBetween(5, 20);
					Random random = new Random();
					int randomIndex1 = random.nextInt(TipoPostazione.values().length);
					TipoPostazione tipoRndm = TipoPostazione.values()[randomIndex1];

					int randomIndex = random.nextInt(edificiDB.size());
					Edificio rndmEdificio = edificiDB.get(randomIndex);

					Postazione postazione = new Postazione(descrizione, numMassimoOccupanti, tipoRndm, rndmEdificio);
					postazioniRepo.save(postazione);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

	}

}
