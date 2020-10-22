package mz.co.scn.pvn.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilizador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String username;
	private String password;
	private String nivel;

	public Utilizador() {
		id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Utilizador [id=" + id + ", username=" + username + ", password=" + password + ", nivel=" + nivel
				+ "]";
	}
	
	

}
