package mz.co.scn.pvn.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.googlecode.genericdao.dao.hibernate.GeneralDAOImpl;

import mz.co.scn.pvn.dao.ParqueDAO;

public class ParqueDAOImpl extends GeneralDAOImpl implements ParqueDAO {
	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
}
