package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Richiesta;
import it.uniroma3.siw.repository.RichiestaRepository;

@Service
public class RichiestaService {

	@Autowired
	private RichiestaRepository richiestaRepository;
	
	public Richiesta inserisci(Richiesta richiesta) {
		return richiestaRepository.save(richiesta);
	}
	
	public Richiesta cercaPerId(Long id) {
		return richiestaRepository.findByPrimaryKey(id);
	}
	
	public List<Richiesta> tutti() {
		return richiestaRepository.findAll();
	}
	
	
	public void cancella(Richiesta richiesta) {
		richiestaRepository.delete(richiesta);
	}
}
