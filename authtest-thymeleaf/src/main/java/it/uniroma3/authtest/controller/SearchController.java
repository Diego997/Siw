package it.uniroma3.authtest.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.authtest.model.Album;
import it.uniroma3.authtest.model.Fotografia;
import it.uniroma3.authtest.model.Fotografo;
import it.uniroma3.authtest.service.FotografiaService;
import it.uniroma3.authtest.service.FunzionarioService;
import it.uniroma3.authtest.service.HibernateSearchService;
import it.uniroma3.authtest.service.RichiestaService;

@Controller
public class SearchController {

	@Autowired
	private FotografiaService fotografiaService;

	@Autowired
	private RichiestaService richiestaService;

	@Autowired
	private FunzionarioService funzionarioService;

	@Autowired
	private HibernateSearchService searchService;



	@RequestMapping("/search")
	public String search(@RequestParam(value = "search", required = false) String q, @RequestParam(value="tipo", required=false) String radio, Model model) {


		if(radio!=null) {
			if(radio.equals("fotografia")) {
				try {
					List<Fotografia> searchResult = null;
					System.out.print(radio);
					searchResult = searchService.fuzzySearchFotografia(q);
					model.addAttribute("fotografie", searchResult);
				} catch (Exception ex) {
					// here you should handle unexpected errors
					// ...
					// throw ex;
				}	
			}

			if(radio.equals("fotografo")) {
				try {
					List<Fotografo> searchResult = null;
					System.out.print(radio);
					searchResult = searchService.fuzzySearchFotografo(q);
					model.addAttribute("fotografi", searchResult);
				} catch (Exception ex) {
					// here you should handle unexpected errors
					// ...
					// throw ex;
				}	
			}

			if(radio.equals("album")) {
				try {
					List<Album> searchResult = null;
					System.out.print(radio);
					searchResult = searchService.fuzzySearchAlbum(q);
					model.addAttribute("albums", searchResult);
				} catch (Exception ex) {
					// here you should handle unexpected errors
					// ...
					// throw ex;
				}	
			}	
		}

		return "search";


	}
}