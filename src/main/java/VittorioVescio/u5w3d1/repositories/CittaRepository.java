package VittorioVescio.u5w3d1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VittorioVescio.u5w3d1.entities.Citta;

@Repository
public interface CittaRepository extends JpaRepository<Citta, UUID> {

}
