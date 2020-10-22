package mz.co.scn.pvn.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Sidónio Goenha
 *
 */
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String nome;
	private String apelido;
	@Temporal(TemporalType.DATE)
	private Date data_nasc;
	private String genero;
	private String nrCelular;
	private String email;
	private String morada;
	@OneToOne(fetch = FetchType.EAGER)
	private Imagem foto;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Utilizador utilizador;
	
	public Cliente() {
		this.id = UUID.randomUUID().toString();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}
	
	

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", apelido=" + apelido + ", data_nasc=" + data_nasc
				+ ", genero=" + genero + ", nrCelular=" + nrCelular + ", email=" + email + ", morada=" + morada
				+ ", foto=" + foto + "]";
	}

	public String getNrCelular() {
		return nrCelular;
	}

	public void setNrCelular(String nrCelular) {
		this.nrCelular = nrCelular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Imagem getFoto() {
		return foto;
	}

	public void setFoto(Imagem foto) {
		this.foto = foto;
	}
	
	
	

}