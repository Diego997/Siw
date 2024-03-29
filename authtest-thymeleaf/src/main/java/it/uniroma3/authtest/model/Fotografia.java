package it.uniroma3.authtest.model;

import javax.persistence.*;


import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Indexed
@Entity
@Table(name="fotografia")
public class Fotografia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column
	private String nome;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column
	private String descrizione;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="img")
	private byte[] img;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="thumb")
	private byte[] thumb;
	
	@IndexedEmbedded
	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fotografia other = (Fotografia) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (primaryKey == null) {
			if (other.primaryKey != null)
				return false;
		} else if (!primaryKey.equals(other.primaryKey))
			return false;
		return true;
	}

	
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
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String desc) {
		this.descrizione = desc;
	}
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] filemode) {
		this.img = filemode;
	}
	public byte[] getThumb() {
		return thumb;
	}
	public void setThumb(byte[] thumb) {
		this.thumb = thumb;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	
}
