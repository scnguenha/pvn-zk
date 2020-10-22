package mz.co.scn.pvn.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Sid�nio Goenha
 *
 */

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@Temporal(TemporalType.DATE)
	private Date data_compra;

	@ManyToOne
	@JoinColumn(name = "parque_id")
	private Parque parque;

	/*@ManyToOne
	@JoinColumn(name = "viatura_id")
	private Viatura viatura;*/

	/*@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;*/

	public Compra() {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


	public Date getData_compra() {
		return data_compra;
	}

	public void setData_compra(Date data_compra) {
		this.data_compra = data_compra;
	}

	public Parque getParque() {
		return parque;
	}

	public void setParque(Parque parque) {
		this.parque = parque;
	}

	/*public Viatura getViatura() {
		return viatura;
	}

	public void setViatura(Viatura viatura) {
		this.viatura = viatura;
	}*/

	/*public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
*/
	/*@Override
	public String toString() {
		return "Compra [id=" + id + ", " + (data_compra != null ? "data_compra=" + data_compra + ", " : "")
				+ (parque != null ? "parque=" + parque + ", " : "")
				+ (viatura != null ? "viatura=" + viatura + ", " : "") + (cliente != null ? "cliente=" + cliente : "")
				+ "]";
	}*/
	
}
