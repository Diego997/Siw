package it.uniroma3.authtest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.service.FotografiaService;
import it.uniroma3.authtest.service.FunzionarioService;
import it.uniroma3.authtest.service.RichiestaService;

@Controller
public class SearchController {

	@Autowired
	private FotografiaService fotografiaService;

	@Autowired
	private RichiestaService richiestaService;

	@Autowired
	private FunzionarioService funzionarioService;


	@RequestMapping("/search")
	public String search(Model model) {

		List<Fotografia> fotografie = fotografiaService.tutti();
		int righe = fotografie.size()/4;
		int mod = fotografie.size()%4; 
		
		model.addAttribute("fotografie", fotografie);
		
		return "search";
	}


}