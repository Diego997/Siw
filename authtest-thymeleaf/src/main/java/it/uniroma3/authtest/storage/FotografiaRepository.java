package it.uniroma3.authtest.storage;

import it.uniroma3.authtest.model.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long>{

	Fotografia findByPrimaryKey(Long id);
	
	Fotografia findByNome(String n);

	//void update(Fotografia fotografia);
	

}
