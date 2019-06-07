package it.uniroma3.siw.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Richiesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	private Cliente cliente;
	private Funzionario funzionario;
	
	private Set<Fotografia> fotografie;

	public Long getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funzionario getFunzionario() {
		return funzionario;
	}

	public void setFunzionario(Funzionario funzionario) {
		this.funzionario = funzionario;
	}

	public Set<Fotografia> getFotografie() {
		return fotografie;
	}

	public void setFotografie(Set<Fotografia> fotografie) {
		this.fotografie = fotografie;
	}
	
}
