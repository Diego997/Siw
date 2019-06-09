package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Fotografia;
import it.uniroma3.siw.service.FotografiaService;

@Component
public class FotografiaValidator implements Validator{
	@Autowired
	private FotografiaService fotografiaService;

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		if (this.fotografiaService.alreadyExists((Fotografia)o)) {
			errors.reject("duplicato");
		}
	}


	@Override
	public boolean supports(Class<?> aClass) {
		return Fotografia.class.equals(aClass);
	}
}
