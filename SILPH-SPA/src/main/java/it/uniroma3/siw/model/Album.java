package it.uniroma3.siw.model;

import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="album")
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@Column
	private String nome;
	@Column
	private String descrizione;

	@OneToMany(targetEntity = Fotografia.class)
	private List<Fotografia> fotografie;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((fotografie == null) ? 0 : fotografie.hashCode());
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
		Album other = (Album) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (fotografie == null) {
			if (other.fotografie != null)
				return false;
		} else if (!fotografie.equals(other.fotografie))
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
	public String getDesc() {
		return descrizione;
	}
	public void setDesc(String desc) {
		this.descrizione = desc;
	}
	public List<Fotografia> getFotografie() {
		return fotografie;
	}
	public void setFotografie(List<Fotografia> fotografie) {
		this.fotografie = fotografie;
	}
}
