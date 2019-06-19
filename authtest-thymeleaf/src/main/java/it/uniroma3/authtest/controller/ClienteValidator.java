package it.uniroma3.authtest.controller;

import it.uniroma3.authtest.model.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClienteValidator implements Validator {
  @Override
  public boolean supports(Class<?> clazz) {
    return Cliente.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");

  }
}
