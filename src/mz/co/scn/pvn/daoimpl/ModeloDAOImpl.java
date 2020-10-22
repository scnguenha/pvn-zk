package mz.co.scn.pvn.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GeneralDAOImpl;

import mz.co.scn.pvn.dao.ModeloDAO;
import mz.co.scn.pvn.model.Marca;
import mz.co.scn.pvn.model.Modelo;

public class ModeloDAOImpl extends GeneralDAOImpl implements ModeloDAO {
	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> findModeloByMarca(Marca marca) {
		// TODO Auto-generated method stub
		return getSession().createQuery(
				"from Modelo m where m.marca.codMarca like '" + marca.getCodMarca() + "'")
				.list();
	}
}
