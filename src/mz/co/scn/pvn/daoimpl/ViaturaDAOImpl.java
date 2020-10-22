package mz.co.scn.pvn.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GeneralDAOImpl;

import mz.co.scn.pvn.dao.ViaturaDAO;
import mz.co.scn.pvn.model.Viatura;
/**
 * 
 * @author Sidónio Goenha
 *
 */
public class ViaturaDAOImpl extends GeneralDAOImpl implements ViaturaDAO {
	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Viatura> findViaturaByKey(String key) {
		// TODO Auto-generated method stub
		return getSession()
				.createQuery(("select distinct v from Viatura v where v.matricula like '%" + key + "%'"
						+ " or v.chassi like '%" + key + "%'" + " or v.transmissao like '%" + key + "%'"
						+ " or v.combustivel like '%" + key + "%'" + " or v.conjuntoTransmissao like '%" + key + "%'"
						+ " or v.cor like '%" + key + "%'" + " or v.modelo.descModelo like '%" + key + "%'"
						+ " or v.modelo.marca.descMarca like '%" + key + "%'" + " or v.parque.nome like '%" + key + "%'"
						+ " or v.preco like '%" + key + "%'" + " or v.anoPub like '%" + key + "%'"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Viatura> findViaturaByParametros(int ano1, int ano2, double cilindrada1, double cilindrada2,
			int lotacao1, int lotacao2, Double preco1, Double preco2, long quilometragem1, long quilometragem2,
			String parametro) {
		// TODO Auto-generated method stub

		return this.getSession()
				.createQuery(("select distinct v from Viatura v where " + " quilometragem between '" + quilometragem1
						+ "'" + " and '" + quilometragem2 + "'" + " and anoPub between '" + ano1 + "'" + " and '" + ano2
						+ "'" + " and cilindrada between '" + cilindrada1 + "'" + " and '" + cilindrada2 + "'"
						+ " and preco between '" + preco1 + "'" + " and '" + preco2 + "'" + " and lotacao between '"
						+ lotacao1 + "'" + " and '" + lotacao2 + "'" + parametro))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Viatura> findViaturaByMarca(String marca) {
		// TODO Auto-generated method stub
		return this.getSession().createQuery("from Viatura v where v.modelo.marca.descMarca like '" + marca + "'")
				.list();

	}

}
