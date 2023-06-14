package VittorioVescio.u5w3d1.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import VittorioVescio.u5w3d1.entities.User;
import VittorioVescio.u5w3d1.exceptions.NotFoundException;
import VittorioVescio.u5w3d1.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService utentiService;

	// GET UTENTI

	@GetMapping("")
	public List<User> getUtenti() {
		return utentiService.findAll();
	}

	// GET SINGOLO UTENTE

	@GetMapping("/{id}")
	public User getById(@PathVariable UUID id) throws NotFoundException {
		return utentiService.findById(id);
	}
}