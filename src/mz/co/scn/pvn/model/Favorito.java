package mz.co.scn.pvn.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Favorito {
	@Id
	private String id;
	private long viatura_id;
	private long cliente_id;
	public long getViatura_id() {
		return viatura_id;
	}
	public void setViatura_id(long viatura_id) {
		this.viatura_id = viatura_id;
	}
	public long getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(long cliente_id) {
		this.cliente_id = cliente_id;
	}	
}
