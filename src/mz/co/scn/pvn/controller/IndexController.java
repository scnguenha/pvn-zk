package mz.co.scn.pvn.controller;

import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Toolbarbutton;

import mz.co.scn.pvn.dao.MarcaDAO;
import mz.co.scn.pvn.dao.ModeloDAO;
import mz.co.scn.pvn.dao.ParqueDAO;
import mz.co.scn.pvn.dao.ViaturaDAO;
import mz.co.scn.pvn.model.Marca;
import mz.co.scn.pvn.model.Parque;
import mz.co.scn.pvn.model.Viatura;
import mz.co.scn.pvn.util.ActionMethods;
import mz.co.scn.pvn.util.DateUtil;

/**
 * 
 * @author Sidónio Goenha
 *
 */

@SuppressWarnings("rawtypes")
public class IndexController extends ActionMethods {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2616203182630671770L;

	@WireVariable
	private ViaturaDAO viaturaDAO;

	@WireVariable
	private ModeloDAO modeloDAO;

	@WireVariable
	private MarcaDAO marcaDAO;

	@WireVariable
	private ParqueDAO parqueDAO;

	@Wire
	private Bandbox bandPesquisa;

	@Wire
	private Listbox listViaturas;

	@Wire
	private Combobox cbMarca, cbModelo, cbTransmissao, cbCombustivel, cbConjuntoTrans, cbCor, cbParque, cbAnoMin,
			cbAnoMax, cbCilindradaMin, cbCilindradaMax, cbLotacaoMin, cbLotacaoMax, cbPrecoMin, cbPrecoMax, cbQuiloMin,
			cbQuiloMax, cbTipoCarroceira;

	/**
	 * Pesquisa na lista by Key
	 **/
	@SuppressWarnings("unchecked")
	@Listen("onOK = #bandPesquisa; onClick = #btPesqKey")
	public void bandPesquisa() {
		preencheListBox(viaturaDAO.findViaturaByKey(bandPesquisa.getText()), listViaturas);
	}

	@SuppressWarnings("unchecked")
	@Listen("onClick = #btPesqParametros")
	public void pesquisaParamentros() {

		String paramentro = "";
		if (!(cbMarca.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.modelo.marca.descMarca like '" + cbMarca.getText() + "'";

		if (!(cbModelo.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.modelo.descModelo like '" + cbModelo.getText() + "'";

		if (!(cbTransmissao.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.transmissao like '" + cbTransmissao.getText() + "'";

		if (!(cbCombustivel.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.combustivel like '" + cbCombustivel.getText() + "'";

		if (!(cbConjuntoTrans.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.conjuntoTransmissao like '" + cbConjuntoTrans.getText() + "'";

		if (!(cbCor.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.cor like '" + cbCor.getText() + "'";

		if (!(cbParque.getSelectedIndex() == -1))
			paramentro = paramentro + " and v.parque.nome like '" + cbParque.getText() + "'";

		List<Viatura> viaturas = viaturaDAO.findViaturaByParametros(
				cbAnoMin.getText() != "" ? Integer.valueOf(cbAnoMin.getText()) : 1950,
				cbAnoMax.getText() != "" ? Integer.valueOf(cbAnoMax.getText()) : DateUtil.currentYear(),
				cbCilindradaMin.getText() != "" ? Double.valueOf(cbCilindradaMin.getText()) : 700,
				cbCilindradaMax.getText() != "" ? Double.valueOf(cbCilindradaMax.getText()) : 4000,
				cbLotacaoMin.getText() != "" ? Integer.valueOf(cbLotacaoMin.getText()) : 2,
				cbLotacaoMax.getText() != "" ? Integer.valueOf(cbLotacaoMax.getText()) : 100,
				cbPrecoMin.getText() != "" ? Double.valueOf(cbPrecoMin.getText()) : 80000,
				cbPrecoMax.getText() != "" ? Double.valueOf(cbPrecoMax.getText()) : 2000000,
				cbQuiloMin.getText() != "" ? Long.valueOf(cbQuiloMin.getText()) : 0,
				cbQuiloMax.getText() != "" ? Long.valueOf(cbQuiloMax.getText()) : 200000, paramentro);

		preencheListBox(viaturas, listViaturas);
	}

	@SuppressWarnings("unchecked")
	@Listen("onCreate = #divIndex")
	public void onCreateDivIndex() {
		preencheComboBoxWithAno(cbAnoMin, 1950);
		preencheComboBoxWithAno(cbAnoMax, 1950);
		preencheComboBoxWithNumber(cbPrecoMin, 80000, 2000000, 5000);
		preencheComboBoxWithNumber(cbPrecoMax, 80000, 2000000, 1500);
		preencheComboBoxWithNumber(cbCilindradaMin, 700, 4000, 300);
		preencheComboBoxWithNumber(cbCilindradaMax, 700, 4000, 300);
		preencheComboBoxWithNumber(cbQuiloMin, 0, 200000, 20000);
		preencheComboBoxWithNumber(cbQuiloMax, 0, 200000, 20000);
		preencheComboBoxWithNumber(cbLotacaoMin, 2, 70, 3);
		preencheComboBoxWithNumber(cbLotacaoMax, 2, 70, 3);

		preencheComboBox(marcaDAO.findAll(Marca.class), cbMarca);

		preencheComboBox(parqueDAO.findAll(Parque.class), cbParque);

		preencheListBox(viaturaDAO.findAll(Viatura.class), listViaturas);

	}

	@SuppressWarnings("unchecked")
	@Listen("onSelect = #cbMarca")
	public void preencheModelo() {
		cbModelo.setSelectedIndex(-1);

		preencheComboBox(modeloDAO.findModeloByMarca((Marca) getSelectedItem(cbMarca)), cbModelo);
	}

	
	@SuppressWarnings("unchecked")
	@Listen("onClick=#tbbToyota,#tbbNissan,#tbbMitsubishi,#tbbHonda,#tbbMercedes,#tbbBmw,#tbbMazda,#tbbSubaru,#tbbVW,"
			+ "#tbbSuzuki,#tbbLandRover,#tbbIsuzu,#tbbAudi,#tbbFord,#tbbDaihatsu,#tbbLexus,#tbbKia,#tbbHino,#tbbPeugeot,"
			+ "#tbbVolvo,#tbbJaguar,#tbbSsangyong")
	public void onClicktbbToyota(Event event) {
		preencheListBox(viaturaDAO.findViaturaByMarca(((Toolbarbutton) event.getTarget()).getLabel()), listViaturas);
	}

}
