package it.uniroma3.authtest.storage;


import it.uniroma3.authtest.model.Richiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RichiestaRepository extends JpaRepository<Richiesta, Long>{
	
	public Richiesta findByPrimaryKey(Long id);
	
	//public void update(Richiesta cliente);
	
}
