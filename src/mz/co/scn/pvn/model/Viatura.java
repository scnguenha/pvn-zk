package mz.co.scn.pvn.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * @author Sidónio Goenha
 *
 */
@Entity
public class Viatura {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String matricula;
	private String chassi;
	private double cilindrada;
	private long quilometragem;
	private int lotacao;
	private String transmissao;
	private int nrPortas;
	private String combustivel;
	private String conjuntoTransmissao;
	private Double preco;
	private String cor;
	private int anoPub;
	private String tipoCarroceira;

	@OneToMany(fetch= FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Imagem> imagens;

	@ManyToOne
	@JoinColumn(name = "modelo_id")
	private Modelo modelo;

	@ManyToOne
	@JoinColumn(name = "parque_id")
	private Parque parque;

	public Viatura() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public double getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(double cilindrada) {
		this.cilindrada = cilindrada;
	}

	public int getLotacao() {
		return lotacao;
	}

	public void setLotacao(int lotacao) {
		this.lotacao = lotacao;
	}

	public String getTransmissao() {
		return transmissao;
	}

	public void setTransmissao(String transmissao) {
		this.transmissao = transmissao;
	}

	public int getNrPortas() {
		return nrPortas;
	}

	public void setNrPortas(int nrPortas) {
		this.nrPortas = nrPortas;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getConjuntoTransmissao() {
		return conjuntoTransmissao;
	}

	public void setConjuntoTransmissao(String conjuntoTransmissao) {
		this.conjuntoTransmissao = conjuntoTransmissao;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Parque getParque() {
		return parque;
	}

	public void setParque(Parque parque) {
		this.parque = parque;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	public int getAnoPub() {
		return anoPub;
	}

	public void setAnoPub(int anoPub) {
		this.anoPub = anoPub;
	}

	public long getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(long quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getTipoCarroceira() {
		return tipoCarroceira;
	}

	public void setTipoCarroceira(String tipoCarroceira) {
		this.tipoCarroceira = tipoCarroceira;
	}

	@Override
	public String toString() {
		return "Viatura [id=" + id + ", " + (matricula != null ? "matricula=" + matricula + ", " : "")
				+ (chassi != null ? "chassi=" + chassi + ", " : "") + "cilindrada=" + cilindrada + ", lotacao="
				+ lotacao + ", " + (transmissao != null ? "transmissao=" + transmissao + ", " : "") + "nrPortas="
				+ nrPortas + ", " + (combustivel != null ? "combustivel=" + combustivel + ", " : "")
				+ (conjuntoTransmissao != null ? "conjuntoTransmissao=" + conjuntoTransmissao + ", " : "")
				+ (preco != null ? "preco=" + preco + ", " : "") + (cor != null ? "cor=" + cor + ", " : "")
				+ (modelo != null ? "modelo=" + modelo + ", " : "") + (parque != null ? "parque=" + parque : "") + "]";
	}

}
