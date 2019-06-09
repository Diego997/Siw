package it.uniroma3.siw.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Fotografia;
import it.uniroma3.siw.repository.FotografiaRepository;
import it.uniroma3.siw.service.FotografiaService;

@Controller
public class FotografiaController {

	@Autowired
	private FotografiaService fotografiaService;

	@Autowired
	private FotografiaValidator fotografiaValidator;

	@InitBinder
	protected void initBinder(final HttpServletRequest request, final ServletRequestDataBinder binder) {
		binder.addValidators(fotografiaValidator);
	}

	@GetMapping("/fotografia/{id}")
	public void showProductImage(@PathVariable Long id,
	                               HttpServletResponse response) throws IOException {
	response.setContentType("image/jpeg"); // Or whatever format you wanna use

	Fotografia fotografia = fotografiaService.cercaPerId(id);

	InputStream is = new ByteArrayInputStream(fotografia.getImg());
	IOUtils.copy(is, response.getOutputStream());
	}
	
	@GetMapping("/uploadImage")
	public String addFotografia(Model model) {
		model.addAttribute("fotografia", new Fotografia());
		return "start";
	}
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @Valid @ModelAttribute("fotografia") Fotografia fotografia, Model model, BindingResult bindingResult ) throws Exception {
		//this.fotografiaValidator.validate(fotografia, bindingResult);
		//if (!bindingResult.hasErrors())
		fotografiaService.salvaFoto(imageFile, fotografia);
		model.addAttribute("fotografia", fotografia.getImg());
		return "foto";
	}

}
