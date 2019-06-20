package it.uniroma3.authtest.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="richiesta")
public class Richiesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	@Column
	private boolean checked;
	
	@ManyToMany(targetEntity = Fotografia.class)
  private List<Fotografia> fotografie=new ArrayList<Fotografia>();

	private final static int MAX_FOTO= 10;

	public Richiesta(){
	  this.checked=false;
  }
  public Richiesta(boolean checked){
	  this.checked=checked;
  }
	
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

	public List<Fotografia> getFotografie() {
		return new ArrayList<Fotografia>(fotografie);
	}

	public void setFotografie(List<Fotografia> fotografie) {
		this.fotografie = fotografie;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

  private Fotografia findLineByCode(Long code) {
    for (Fotografia line : this.fotografie) {
      if (line.getPrimaryKey().equals(code)) {
        return line;
      }
    }
    return null;
  }

  public void addFoto(Fotografia foto) {
    Fotografia line= this.findLineByCode(foto.getPrimaryKey());
    if (line==null && this.fotografie.size()<MAX_FOTO){
      this.fotografie.add(foto);

    }

  }
  public void removeFoto(Fotografia foto) {
    Fotografia line = this.findLineByCode(foto.getPrimaryKey());
    if (line != null) {
      this.fotografie.remove(line);
    }
  }

  public boolean isEmpty() {
    if(this.fotografie!=null)
      return this.fotografie.isEmpty();
    return true;
  }

  public boolean isValidCustomer() {
    return this.cliente != null && this.cliente.isValid();
  }






}
