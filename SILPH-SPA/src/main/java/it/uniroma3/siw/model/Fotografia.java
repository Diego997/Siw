package it.uniroma3.siw.model;

import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name="fotografia")
public class Fotografia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@Column
	private String nome;
	@Column
	private String desc;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] img;
	
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
	public byte[] getImg() {
		return img;
	}
	public void setImg(byte[] img) {
		this.img = img;
	}
	
}
