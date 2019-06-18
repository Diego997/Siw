package it.uniroma3.authtest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.service.FotografiaService;

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

	@RequestMapping("/images")
	public String showFotografie(Model model){
		model.addAttribute("fotografie1", fotografiaService.tutti().subList(0, fotografiaService.tutti().size()/2));
		model.addAttribute("fotografie2", fotografiaService.tutti().subList(fotografiaService.tutti().size()/2, fotografiaService.tutti().size()));
		return "index";
	}
	@GetMapping("/fotografia/{id}")
	public String showFotografiaImage(@PathVariable Long id, Model model) {

	Fotografia fotografia = fotografiaService.cercaPerId(id);

		model.addAttribute("fotografia", fotografia);
		return "foto";
	}
	
	@GetMapping("/uploadImage")
	public String addFotografia(Model model) {
		model.addAttribute("fotografia", new Fotografia());
		return "addfoto";
	}

	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imgFile") MultipartFile imageFile, Model model, @Valid @ModelAttribute("fotografia") Fotografia fotografia, BindingResult bindingResult ){
		this.fotografiaValidator.validate(fotografia, bindingResult);
		if (!bindingResult.hasErrors() && imageFile.getSize()>0) {
            fotografiaService.salvaFoto(imageFile, fotografia);
            model.addAttribute("fotografia", fotografia);
            return "foto";
        }
		else {
			return "addfoto";
		}
	}

}
