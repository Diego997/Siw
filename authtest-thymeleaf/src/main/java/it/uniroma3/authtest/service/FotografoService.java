package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.storage.FotografoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.Valid;

@Service
public class FotografoService {
	@Autowired
	private FotografoRepository fotografoRepository;

	@Transactional
	public Fotografo inserisci(Fotografo fotografo) {
		return fotografoRepository.save(fotografo);
	}

	@Transactional
	public List<Fotografo> FotografoPerNome(String nome) {
		return fotografoRepository.findByNome(nome);
	}

	@Transactional
	public List<Fotografo> tutti() {
		return fotografoRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Fotografo fotografo) {
		return fotografoRepository.findByPrimaryKey(fotografo.getPrimaryKey()) != null;
	}

	public void salva(@Valid Fotografo fotografo) {
		fotografoRepository.save(fotografo);
	}
}
