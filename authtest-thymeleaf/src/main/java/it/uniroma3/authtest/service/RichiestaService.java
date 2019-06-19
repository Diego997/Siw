package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Richiesta;
import it.uniroma3.authtest.storage.RichiestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
  @Transactional
  public boolean alreadyExists(Richiesta richiesta) {
    return richiestaRepository.findByPrimaryKey(richiesta.getPrimaryKey()) != null;
  }
	
	public void cancella(Richiesta richiesta) {
		richiestaRepository.delete(richiesta);
	}
}
