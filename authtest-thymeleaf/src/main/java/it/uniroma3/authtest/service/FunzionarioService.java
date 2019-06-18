package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Funzionario;
import it.uniroma3.authtest.storage.FunzionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FunzionarioService {
	@Autowired
	private FunzionarioRepository funzionarioRepository;

	@Transactional
	public Funzionario inserisci(Funzionario funzionario) {
		return funzionarioRepository.save(funzionario);
	}

	@Transactional
	public Funzionario funzionarioPerEmail(String email) {
		return funzionarioRepository.findByEmail(email);
	}

	@Transactional
	public List<Funzionario> tutti() {
		return funzionarioRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Funzionario funzionario) {
		return funzionarioRepository.findByEmail(funzionario.getEmail()) != null;
	}
}
