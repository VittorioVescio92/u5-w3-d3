package VittorioVescio.u5w3d1;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import VittorioVescio.u5w3d1.entities.Citta;
import VittorioVescio.u5w3d1.entities.Edificio;
import VittorioVescio.u5w3d1.repositories.CittaRepository;
import VittorioVescio.u5w3d1.repositories.EdificiRepository;

@Component
public class EdificiRunner implements CommandLineRunner {
	@Autowired
	EdificiRepository edificiRepo;
	@Autowired
	CittaRepository cittaRepo;
	@Autowired
	private PasswordEncoder pswEncoder;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));

		List<Edificio> edificiDB = edificiRepo.findAll();
		List<Citta> cittaDB = cittaRepo.findAll();

		if (edificiDB.size() == 0) {
			for (int i = 0; i < 10; i++) {
				try {
					String nome = faker.pokemon().name();
					String indirizzo = faker.address().fullAddress();
					int randomIndex = new Random().nextInt(cittaDB.size());
					Citta randomCitta = cittaDB.get(randomIndex);
					String rdmSafetyCode = pswEncoder.encode(randomCode(8));
					Edificio edificio = new Edificio(nome, indirizzo, randomCitta, rdmSafetyCode);
					edificiRepo.save(edificio);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}

	private String randomCode(int length) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+.,/*";
		StringBuilder code = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			code.append(characters.charAt(index));
		}
		return code.toString();
	}

}
