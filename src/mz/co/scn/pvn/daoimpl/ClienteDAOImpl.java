package mz.co.scn.pvn.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GeneralDAOImpl;

import mz.co.scn.pvn.dao.ClienteDAO;
import mz.co.scn.pvn.model.Cliente;
/**
 * 
 * @author Sidónio Goenha
 *
 */
public class ClienteDAOImpl extends GeneralDAOImpl implements ClienteDAO {

	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> findClienteByKey(String key) {
		return getSession()
				.createQuery("select distinct from Cliente where nome like '%" + key + "%'" + " or apelido like '%"
						+ key + "%'" + " or genero like '%" + key + "%'" + " or nrCelular like '%" + key + "%'"
						+ " or morada like '%" + key + "%'" + " or email like '%" + key + "%'")
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}
