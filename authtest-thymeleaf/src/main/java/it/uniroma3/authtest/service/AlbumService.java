package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.storage.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
