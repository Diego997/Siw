package it.uniroma3.siw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import it.uniroma3.siw.model.Fotografia;

@Repository
public interface FotografiaRepository extends JpaRepository<Fotografia, Long>{

	Fotografia findByPrimaryKey(Long id);

	//void update(Fotografia fotografia);
	

}
