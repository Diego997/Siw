package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Cliente;
import it.uniroma3.siw.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	public Cliente inserisci(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Transactional
	public Cliente ClientePerEmail(String email) {
		return clienteRepository.findByEmail(email);
	}

	@Transactional
	public List<Cliente> tutti() {
		return clienteRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Cliente cliente) {
		return clienteRepository.findByEmail(cliente.getEmail()) != null;
	}
}
