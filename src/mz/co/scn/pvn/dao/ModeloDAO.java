package mz.co.scn.pvn.dao;

import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GeneralDAO;

import mz.co.scn.pvn.model.Marca;
import mz.co.scn.pvn.model.Modelo;

public interface ModeloDAO extends GeneralDAO{
	
	public List<Modelo> findModeloByMarca(Marca marca);
}
