package it.uniroma3.authtest.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="richiesta")
public class Richiesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Funzionario funzionario;
	
	@OneToMany(targetEntity = Fotografia.class)
	private List<Fotografia> fotografie;

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

	public List<Fotografia> getFotografie() {
		return fotografie;
	}

	public void setFotografie(List<Fotografia> fotografie) {
		this.fotografie = fotografie;
	}
	
}
