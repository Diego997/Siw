package it.uniroma3.siw.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="fotografo")
public class Fotografo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@Column
	private String nome;
	@Column
	private String cognome;
	@OneToMany(targetEntity = Album.class)
	private List<Album> album;
	
	public Long getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Long primaryKey) {
		this.primaryKey = primaryKey;
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
	public List<Album> getAlbum() {
		return album;
	}
	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	
}
