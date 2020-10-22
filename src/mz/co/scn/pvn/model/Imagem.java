package mz.co.scn.pvn.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Sidónio Goenha
 *
 */
@Entity
public class Imagem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String descImagem;
	private double size;
	
	private byte[] bs;
	
	public Imagem() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getDescImagem() {
		return descImagem;
	}

	public void setDescImagem(String descImagem) {
		this.descImagem = descImagem;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public byte[] getBs() {
		return bs;
	}

	public void setBs(byte[] bs) {
		this.bs = bs;
	}

	
	
}
