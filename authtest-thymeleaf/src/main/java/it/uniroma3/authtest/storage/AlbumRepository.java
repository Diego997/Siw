package it.uniroma3.authtest.storage;

import it.uniroma3.authtest.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long>{
	
	public List<Album> findByNome(String nome);

	public Album findByPrimaryKey(Long primaryKey);
}
