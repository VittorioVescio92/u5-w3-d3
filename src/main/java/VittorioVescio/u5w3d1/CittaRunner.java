package VittorioVescio.u5w3d1;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import VittorioVescio.u5w3d1.entities.Citta;
import VittorioVescio.u5w3d1.repositories.CittaRepository;

@Component
public class CittaRunner implements CommandLineRunner {

	@Autowired
	CittaRepository cittaRepo;

	@Override
	public void run(String... args) throws Exception {

		Faker faker = new Faker(new Locale("it"));

		List<Citta> cittaDB = cittaRepo.findAll();

		if (cittaDB.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {

					String cityName = faker.address().cityName();

					Citta citta = new Citta(cityName);
					cittaRepo.save(citta);
				} catch (Exception e) {
					System.out.println(e);
				}
			}

		}

	}
}
