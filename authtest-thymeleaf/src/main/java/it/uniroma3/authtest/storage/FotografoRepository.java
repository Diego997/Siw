package it.uniroma3.authtest.storage;

import it.uniroma3.authtest.model.Fotografo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FotografoRepository extends JpaRepository<Fotografo, Long>{
	
	public List<Fotografo> findByNome(String nome);
	
	public List<Fotografo> findByCognome(String nome);

	public Fotografo findByPrimaryKey(Long primaryKey);
}
