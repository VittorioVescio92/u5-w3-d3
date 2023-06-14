package VittorioVescio.u5w3d1.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import VittorioVescio.u5w3d1.entities.Prenotazione;
import VittorioVescio.u5w3d1.entities.payloads.PrenotazionePayload;
import VittorioVescio.u5w3d1.services.PrenotazioniService;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
	@Autowired
	private PrenotazioniService prenotazioniService;

	@GetMapping("")
	public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
		return prenotazioniService.find(page, size, sortBy);
	}

	@GetMapping("/{id}")
	public Prenotazione getById(@PathVariable UUID id) throws Exception {
		return prenotazioniService.findById(id);
	}

	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public Prenotazione createPrenotazione(@RequestBody PrenotazionePayload body) {
		return prenotazioniService.create(body);
	}

	@PutMapping("/{id}")
	public Prenotazione findByIdAndUpdate(@PathVariable UUID id, @RequestBody PrenotazionePayload body) {
		return prenotazioniService.findByIdAndUpdate(id, body);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePrenotazione(@PathVariable UUID id) {
		prenotazioniService.findByIdAndDelete(id);
	}
}
