package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

	@Id
	private String email;

	@Column
	private String nome;
	@Column
	private String cognome;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


}
