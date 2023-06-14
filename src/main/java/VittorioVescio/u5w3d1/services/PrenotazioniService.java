package VittorioVescio.u5w3d1.services;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import VittorioVescio.u5w3d1.entities.Postazione;
import VittorioVescio.u5w3d1.entities.Prenotazione;
import VittorioVescio.u5w3d1.entities.User;
import VittorioVescio.u5w3d1.entities.payloads.PrenotazionePayload;
import VittorioVescio.u5w3d1.exceptions.BadRequestException;
import VittorioVescio.u5w3d1.exceptions.NotFoundException;
import VittorioVescio.u5w3d1.repositories.PrenotazioniRepository;

@Service
public class PrenotazioniService {
	@Autowired
	private PrenotazioniRepository prenotazioniRepo;

	@Autowired
	private PostazioniService postazioniService;

	@Autowired
	private UsersService usersService;

	public Page<Prenotazione> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return prenotazioniRepo.findAll(pageable);
	}

	public Prenotazione findById(UUID id) throws NotFoundException {
		return prenotazioniRepo.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata!"));
	}

	public Prenotazione create(PrenotazionePayload p) {
		Postazione postazione = postazioniService.findById(p.getPostazioneId());

		prenotazioniRepo.findByPostazioneAndDataPrenotata(postazione, p.getDataPrenotata()).ifPresent(user -> {
			throw new BadRequestException("Postazione " + p.getPostazioneId() + " già in uso!");
		});

		LocalDate twoDaysAhead = LocalDate.now().plusDays(2);
		User user = usersService.findById(p.getUserId());
		if (p.getDataPrenotata().isAfter(twoDaysAhead)) {
			Prenotazione newPrenotazione = new Prenotazione(user, postazione, p.getDataPrenotata(), LocalDate.now());
			return prenotazioniRepo.save(newPrenotazione);
		} else {
			throw new BadRequestException("Devono esserci almeno due giorni prima della data di presentazione.");
		}

	}

	public Prenotazione findByIdAndUpdate(UUID id, PrenotazionePayload p) throws NotFoundException {
		Prenotazione found = this.findById(id);

		Postazione postazione = postazioniService.findById(p.getPostazioneId());

		LocalDate twoDaysAhead = LocalDate.now().plusDays(2);
		User user = usersService.findById(p.getUserId());
		if (p.getDataPrenotata().isAfter(twoDaysAhead)) {
			found.setId(id);
			found.setPostazione(postazione);
			found.setUser(user);
			found.setDataPrenotata(p.getDataPrenotata());
			return prenotazioniRepo.save(found);
		} else {
			throw new BadRequestException("Devono esserci almeno due giorni prima della data di presentazione.");
		}

	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Prenotazione found = this.findById(id);
		prenotazioniRepo.delete(found);
	}
}
