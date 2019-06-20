package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Richiesta;
import it.uniroma3.authtest.storage.RichiestaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RichiestaService {

	@Autowired
	private RichiestaRepository richiestaRepository;
	
	public Richiesta inserisci(Richiesta richiesta) {
		return richiestaRepository.save(richiesta);
	}
	
	public Richiesta cercaPerId(Long id) {
		return richiestaRepository.getOne(id);
	}
	
	public List<Richiesta> tuttiNonGestiti() {
      List<Richiesta> rList= richiestaRepository.findAll();
    List<Richiesta> rList1= new ArrayList<Richiesta>();
      for(Richiesta r:rList)
        if (!r.isChecked())
          rList1.add(r);

      return rList1;
	}
  @Transactional
  public boolean alreadyExists(Richiesta richiesta) {
    return richiestaRepository.findByPrimaryKey(richiesta.getPrimaryKey()) != null;
  }
	
	public void cancella(Richiesta richiesta) {
		richiestaRepository.delete(richiesta);
	}
	@Transactional(readOnly = false)
	public void setCheckedTrue(Long id){
	  Richiesta r=cercaPerId(id);
	  richiestaRepository.setCheckedTrue(id);
	this.inserisci(r);}
}
