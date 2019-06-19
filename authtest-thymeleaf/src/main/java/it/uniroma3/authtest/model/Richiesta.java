package it.uniroma3.authtest.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="richiesta")
public class Richiesta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long primaryKey;
	
	@OneToOne
	private Cliente cliente;

	private boolean checked;
	
	@OneToMany(targetEntity = Fotografia.class)
	private List<Fotografia> fotografie=new ArrayList<Fotografia>();

	private final static int MAX_FOTO= 10;

	
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
		return fotografie;
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
      return false;//////////////////////////////////////
    //return this.fotografie.isEmpty();
    return false;
  }

  public boolean isValidCustomer() {
    return this.cliente != null && this.cliente.isValid();
  }






}
