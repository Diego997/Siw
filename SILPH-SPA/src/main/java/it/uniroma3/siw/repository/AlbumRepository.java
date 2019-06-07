package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.siw.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long>{
	
	public List<Album> findByNome(String nome);

	public Album findByPrimaryKey(Long primaryKey);
}
