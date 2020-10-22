package mz.co.scn.pvn.dao;



import java.util.List;

import com.googlecode.genericdao.dao.hibernate.GeneralDAO;

import mz.co.scn.pvn.model.Viatura;

public interface ViaturaDAO extends GeneralDAO {
	public List<Viatura> findViaturaByKey(String key);

	public List<Viatura> findViaturaByParametros(int ano1, int ano2, double cilindrada1, double cilindrada2,
			int lotacao1, int lotacao2, Double preco1, Double preco2, long quilometragem1, long quilometragem2,
			String parametro);

	public List<Viatura> findViaturaByMarca(String marca);

}
