package it.uniroma3.siw.model;

import javax.persistence.*;

@Entity
@Table(name="funzionario")
public class Funzionario {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", columnDefinition = "serial", nullable = false)
  private Long id;
  @Column(name = "email", unique=true)
	private String email;
  @Column(name = "pwd")
	private String pwd;
  @Column(name="nome")
	private String nome;
  @Column(name="cognome")
	private String cognome;
  @Column(name = "role")
  private String role;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Funzionario(Long id, String firstName, String lastName, String email, String password, String role) {
    this.id = id;
    this.nome = firstName;
    this.cognome = lastName;
    this.email = email;
    this.pwd= password;
    this.role = role;
  }
  public Funzionario() {
  }


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
