package it.uniroma3.authtest.storage;

import it.uniroma3.authtest.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public Cliente findByEmail(String email);
	
	
}
