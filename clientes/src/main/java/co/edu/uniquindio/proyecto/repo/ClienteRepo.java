package co.edu.uniquindio.proyecto.repo;

import co.edu.uniquindio.proyecto.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByEmail(String email);

}
