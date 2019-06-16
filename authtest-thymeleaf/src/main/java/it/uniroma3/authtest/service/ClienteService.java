package it.uniroma3.authtest.service;

import it.uniroma3.authtest.model.Cliente;
import it.uniroma3.authtest.storage.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
