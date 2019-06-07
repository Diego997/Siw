package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Album;
import it.uniroma3.siw.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;

	@Transactional
	public Album inserisci(Album album) {
		return albumRepository.save(album);
	}

	@Transactional
	public List<Album> AlbumPerNome(String nome) {
		return albumRepository.findByNome(nome);
	}

	@Transactional
	public List<Album> tutti() {
		return albumRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Album album) {
		return albumRepository.findByPrimaryKey(album.getPrimaryKey()) != null;
	}
}
