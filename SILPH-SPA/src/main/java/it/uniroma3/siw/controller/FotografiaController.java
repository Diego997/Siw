package it.uniroma3.siw.controller;

import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Fotografia;
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

	@GetMapping("/uploadImage")
	public String addFotografia(Model model) {
		model.addAttribute("fotografia", new Fotografia());
		return "start";
	}
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @Valid @ModelAttribute("fotografia") Fotografia fotografia, Model model, BindingResult bindingResult ) {
		this.fotografiaValidator.validate(fotografia, bindingResult);
		if (!bindingResult.hasErrors()) {
			try {
				fotografiaService.salvaFoto(imageFile, fotografia);
				model.addAttribute("fotografie", this.fotografiaService.tutti());
				return "foto";
			}catch (Exception e) {
				return "start";
			}
		}
		return "start";
	}

}
