package it.uniroma3.authtest.model;

import javax.persistence.*;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import java.util.List;

@Indexed
@Entity
@Table(name="fotografo")
public class Fotografo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;

	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column
	private String nome;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column
	private String cognome;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column
	private String descrizione;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="img")
	private byte[] img;
	
	@IndexedEmbedded(depth = 1)
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
