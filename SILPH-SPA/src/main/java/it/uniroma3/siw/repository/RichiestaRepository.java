package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import it.uniroma3.siw.model.Richiesta;

@Repository
public interface RichiestaRepository extends JpaRepository<Richiesta, Long>{
	
	public Richiesta findByPrimaryKey(Long id);
	
	//public void update(Richiesta cliente);
	
}
