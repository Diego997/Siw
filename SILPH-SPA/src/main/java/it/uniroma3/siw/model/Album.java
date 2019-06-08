package it.uniroma3.siw.model;

import java.util.Set;
import javax.persistence.*;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@Column
	private String nome;
	@Column
	private String desc;
	@OneToMany
	private Set<Fotografia> fotografie;
	
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
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Set<Fotografia> getFotografie() {
		return fotografie;
	}
	public void setFotografie(Set<Fotografia> fotografie) {
		this.fotografie = fotografie;
	}
	
}
