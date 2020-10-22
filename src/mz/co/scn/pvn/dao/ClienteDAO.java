package mz.co.scn.pvn.dao;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GeneralDAO;

import mz.co.scn.pvn.model.Cliente;

public interface ClienteDAO extends GeneralDAO {
	
	public List<Cliente> findClienteByKey(String key);
}
