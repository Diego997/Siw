package it.uniroma3.siw.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Fotografia;
import it.uniroma3.siw.repository.FotografiaRepository;

@Service
public class FotografiaService {

	@Autowired
	private FotografiaRepository fotografiaRepository;
	
	public Fotografia inserisci(Fotografia fotografia) {
		return fotografiaRepository.save(fotografia);
	}
	
	public Fotografia cercaPerId(Long id) {
		return fotografiaRepository.findByPrimaryKey(id);
	}
	
	public List<Fotografia> tutti() {
		return fotografiaRepository.findAll();
	}
	
	
	public void cancella(Fotografia fotografia) {
		fotografiaRepository.delete(fotografia);
	}
	
	public void salvaFoto(MultipartFile imageFile, Fotografia fotografia){
		byte[] bytes = new byte[0];
		try {
			bytes = imageFile.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fotografia.setImg(bytes);
		fotografiaRepository.save(fotografia);
	}

	public boolean alreadyExists(Fotografia o) {
		// TODO Auto-generated method stub
		return false;
	}
}
