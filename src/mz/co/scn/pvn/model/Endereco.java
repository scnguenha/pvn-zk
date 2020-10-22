package mz.co.scn.pvn.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Sid�nio Goenha
 *
 */
@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String provincia;
	private String bairro;
	private String nrParcela;
	private String av_rua;

	@ManyToOne
	@JoinColumn(name = "parque_id")
	private Parque parque;

	public Endereco() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNrParcela() {
		return nrParcela;
	}

	public void setNrParcela(String nrParcela) {
		this.nrParcela = nrParcela;
	}

	public String getAv_rua() {
		return av_rua;
	}

	public void setAv_rua(String av_rua) {
		this.av_rua = av_rua;
	}

	public Parque getParque() {
		return parque;
	}

	public void setParque(Parque parque) {
		this.parque = parque;
	}

}
