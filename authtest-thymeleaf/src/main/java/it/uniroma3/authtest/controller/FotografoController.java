package it.uniroma3.authtest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.service.FotografoService;

@Controller
public class FotografoController {

	@Autowired
	private FotografoService fotografoService;
	
	@Autowired
	private FotografoValidator fotografoValidator;
	
	@GetMapping("/addfotografo")
	public String addFotografia(Model model) {
		model.addAttribute("fotografo", new Fotografo());
		return "addfotografo";
	}
	
	@PostMapping("/addfotografo")
	public String uploadImage(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model, BindingResult bindingResult ){
		this.fotografoValidator.validate(fotografo, bindingResult);
		if (!bindingResult.hasErrors()) {
            fotografoService.salva(fotografo);
            model.addAttribute("fotografo", fotografo);
            return "admin";
        }
		else {
			return "addfotografo";
		}
	}

}
