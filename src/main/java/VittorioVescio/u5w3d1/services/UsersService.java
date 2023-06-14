package VittorioVescio.u5w3d1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VittorioVescio.u5w3d1.entities.User;
import VittorioVescio.u5w3d1.entities.payloads.UserRegistrationPayload;
import VittorioVescio.u5w3d1.exceptions.BadRequestException;
import VittorioVescio.u5w3d1.exceptions.NotFoundException;
import VittorioVescio.u5w3d1.repositories.UsersRepository;

@Service
public class UsersService {
	@Autowired
	UsersRepository usersRepo;

	public List<User> findAll() {
		return usersRepo.findAll();
	}

	public User findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato!"));

	}

	public User create(UserRegistrationPayload u) {

		usersRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email " + user.getEmail() + " already in use!");
		});
		User newUser = new User(u.getUsername(), u.getNome(), u.getEmail(), u.getPassword());
		return usersRepo.save(newUser);
	}

	public User findByEmail(String email) throws NotFoundException {
		return usersRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con questa mail: " + email + " non trovato!"));
	}
}
