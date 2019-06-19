package it.uniroma3.authtest.model;

import javax.persistence.*;
import java.util.List;

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
	@Column
	private String descrizione;
	@Column(name="img")
	private byte[] img;
	@OneToMany(targetEntity = Album.class, cascade = {CascadeType.ALL})
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
}
