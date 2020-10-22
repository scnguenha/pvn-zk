package mz.co.scn.pvn.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Sid�nio Goenha
 *
 */

@Entity
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String codModelo;
	private String descModelo;

	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;

	@OneToMany
	private List<Viatura> viaturas;

	public Modelo() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}

	public String getDescModelo() {
		return descModelo;
	}

	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Viatura> getViaturas() {
		return viaturas;
	}

	public void setViaturas(List<Viatura> viaturas) {
		this.viaturas = viaturas;
	}

	@Override
	public String toString() {
		return descModelo;
	}

}
