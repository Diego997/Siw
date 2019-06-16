package it.uniroma3.authtest.storage;

import it.uniroma3.authtest.model.Funzionario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface is a JpaRepository for storage operations on Users.
 *
 * @see it.uniroma3.authtest.model.Funzionario
 */
public interface FunzionarioRepository extends JpaRepository<Funzionario, Long> {

}
