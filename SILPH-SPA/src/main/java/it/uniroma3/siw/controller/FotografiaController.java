package it.uniroma3.siw.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

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
	public String showFotografiaImage(@PathVariable Long id, Model model) {

	Fotografia fotografia = fotografiaService.cercaPerId(id);

		model.addAttribute("fotografia", fotografia);
		model.addAttribute("img", Base64.getEncoder().encodeToString(fotografia.getImg()));
		return "foto";
	}
	
	@GetMapping("/uploadImage")
	public String addFotografia(Model model) {
		model.addAttribute("fotografia", new Fotografia());
		return "start";
	}

	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imgFile") MultipartFile imageFile, Model model, @Valid @ModelAttribute("fotografia") Fotografia fotografia, BindingResult bindingResult ){
		this.fotografiaValidator.validate(fotografia, bindingResult);
		if (!bindingResult.hasErrors() && imageFile.getSize()>0) {
            fotografiaService.salvaFoto(imageFile, fotografia);
            model.addAttribute("fotografia", fotografia);
			model.addAttribute("img", Base64.getEncoder().encodeToString(fotografia.getImg()));
            return "foto";
        }
		else {
			return "start";
		}
	}

}
