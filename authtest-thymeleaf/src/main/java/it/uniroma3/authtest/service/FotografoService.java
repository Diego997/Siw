package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.storage.FotografoRepository;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
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

	public void salva(MultipartFile imageFile, Fotografo fotografo){
		byte[] bytes = new byte[0];
		try {
			InputStream is = imageFile.getInputStream();
			BufferedImage img = ImageIO.read(is);  
			Dimension dimension = getScaledDimension(new Dimension(img.getWidth(), img.getHeight()), new Dimension(250, 250));
			img = Scalr.resize(img, dimension.width, dimension.height);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, imageFile.getContentType().replace("image/", ""), baos);
			bytes = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fotografo.setImg(bytes);
		fotografoRepository.save(fotografo);
	}
	
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;

	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }

	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }

	    return new Dimension(new_width, new_height);
	}
}
