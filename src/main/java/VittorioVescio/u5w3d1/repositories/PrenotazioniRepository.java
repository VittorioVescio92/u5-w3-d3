package VittorioVescio.u5w3d1.repositories;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VittorioVescio.u5w3d1.entities.Postazione;
import VittorioVescio.u5w3d1.entities.Prenotazione;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {
	Optional<Prenotazione> findByPostazioneAndDataPrenotata(Postazione postazione, LocalDate data);
}
