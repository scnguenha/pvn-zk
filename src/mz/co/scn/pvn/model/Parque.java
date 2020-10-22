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
public class Parque {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String nome;

	@OneToMany
	private List<Viatura> viaturas;

	@OneToMany
	private List<Endereco> enderecos;

	@OneToMany
	private List<Compra> compras;

	public Parque() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Viatura> getViaturas() {
		return viaturas;
	}

	public void setViaturas(List<Viatura> viaturas) {
		this.viaturas = viaturas;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	@Override
	public String toString() {
		return nome;
	}

}
