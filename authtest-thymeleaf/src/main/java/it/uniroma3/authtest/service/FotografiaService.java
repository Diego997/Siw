package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.storage.FotografiaRepository;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

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
		byte[] thumb = new byte[0];
		try {
			InputStream is = imageFile.getInputStream();
			BufferedImage img = ImageIO.read(is);  
			Dimension dimension = getScaledDimension(new Dimension(img.getWidth(), img.getHeight()), new Dimension(1000, 1000));
			img = Scalr.resize(img, dimension.width, dimension.height);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(img, imageFile.getContentType().replace("image/", ""), baos);
			bytes = baos.toByteArray();
			
			InputStream is2 = imageFile.getInputStream();
			BufferedImage img2 = ImageIO.read(is2);  
			Dimension dimension2 = getScaledDimension(new Dimension(img2.getWidth(), img2.getHeight()), new Dimension(360, 360));
			img2 = Scalr.resize(img2, dimension2.width, dimension2.height);
			ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
			ImageIO.write(img2, imageFile.getContentType().replace("image/", ""), baos2);
			thumb = baos2.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fotografia.setImg(bytes);
		fotografia.setThumb(thumb);
		fotografiaRepository.save(fotografia);
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

	public boolean alreadyExists(Fotografia o) {

		return false;
	}
}
