package mz.co.scn.pvn.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author Sid�nio Goenha
 *
 */
@Entity
public class Marca {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String codMarca;
	private String descMarca;
	
	@OneToMany
	private List<Modelo> modelos;

	public Marca() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
	
	
	public String getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}

	@Override
	public String toString() {
		return descMarca;
	}
	
}
