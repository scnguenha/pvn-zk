package mz.co.scn.pvn.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import mz.co.scn.pvn.dao.ImagemDAO;
import mz.co.scn.pvn.dao.MarcaDAO;
import mz.co.scn.pvn.dao.ModeloDAO;
import mz.co.scn.pvn.dao.ParqueDAO;
import mz.co.scn.pvn.dao.ViaturaDAO;
import mz.co.scn.pvn.model.Imagem;
import mz.co.scn.pvn.model.Marca;
import mz.co.scn.pvn.model.Modelo;
import mz.co.scn.pvn.model.Parque;
import mz.co.scn.pvn.model.Viatura;
import mz.co.scn.pvn.util.ActionMethods;

/**
 * @author Sidónio Goenha
 *
 */
@SuppressWarnings("rawtypes")
public class ViaturaController extends ActionMethods {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Button btAddFoto, btGravar, btCancelar;
	@Wire
	private Div div_fotos, div_lista, divRegViatura;

	@Wire
	private Image imgFoto, imgFoto1, imgFoto2, imgFoto3, imgFoto4, imageview;

	@Wire
	private Longbox lbQuilometragem;

	@Wire
	private Combobox cbMarca, cbModelo, cbTransmissao, cbCombustivel, cbConjuntoTrans, cbCor, cbParque, cbAnoMin,
			cbAnoMax, cbCilindradaMin, cbCilindradaMax, cbLotacaoMin, cbLotacaoMax, cbPrecoMin, cbPrecoMax, cbQuiloMin,
			cbQuiloMax, cbTipoCarroceira;

	@Wire
	private Doublebox dbCilindrada, dbPreco;

	@Wire
	private Intbox ibLotacao, ibNrPortas, ibAno;

	@Wire
	private Textbox tbChassi, tbMatricula;

	@Wire
	private Listbox listViaturas, listImages;

	@Wire
	private Window winListaPrincipal, winDetalhesViatura;

	@Wire
	private Label lbMarca, lbModelo, lbTransmissao, lbCombustivel, lbConjuntoTrans, lbCor, lbParque, lbCilindrada,
			lbPreco, lbLotacao, lbNrPortas, lbChassi, lbMatricula, lbAno;

	@Wire
	private Row rPassword, rPassword1, rUsername;

	@WireVariable
	private MarcaDAO marcaDAO;

	@WireVariable
	private ParqueDAO parqueDAO;

	@WireVariable
	private ViaturaDAO viaturaDAO;
	
	@WireVariable
	private ModeloDAO modeloDAO;
	
	@WireVariable
	private ImagemDAO imagemDAO;

	private int i = 0;

	private List<byte[]> lstBytes = new ArrayList<>();
	private List<BufferedImage> lstBufferedImage = new ArrayList<>();
	private List<Image> lstImages = new ArrayList<>();

	private byte[][] bytes = new byte[5][];
	private BufferedImage[] bufferedImages = new BufferedImage[5];

	private Image[] listaImage = { imgFoto, imgFoto1, imgFoto2, imgFoto3, imgFoto4 };
	private List<Image> lstImage = new ArrayList<>();

	@Wire
	private Bandbox bandPesquisa;

	@Listen("onClick=#toyota")
	public void panelClick() {
		mensagem("OLá");
	}

	@Listen("onCreate = #divRegViatura;onCreate = #divIndex")
	public void onCreate() {

		preencheComboBox(marcaDAO.findAll(Marca.class), cbMarca);

		preencheComboBox(parqueDAO.findAll(Parque.class), cbParque);

		preencheListBox(viaturaDAO.findAll(Viatura.class), listViaturas);

		session.setAttribute("lastPath", "/viatura/pages/RegistoViatura.zul");
	}

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

		preencheComboBox(marcaDAO.findAll(Marca.class), cbModelo);

		lstImage.add(imgFoto);
		lstImage.add(imgFoto1);
		lstImage.add(imgFoto2);
		lstImage.add(imgFoto3);
		lstImage.add(imgFoto4);
	}

	@Listen("onCreate = #winDetalhesViatura")
	public void onCreateDetalhes() throws IOException {
		Viatura viatura = (Viatura) session.getAttribute("viatura");
		if (viatura != null) {
			lbMarca.setValue(viatura.getModelo().getMarca().getDescMarca());
			lbModelo.setValue(viatura.getModelo().getDescModelo());
			lbAno.setValue(viatura.getAnoPub() + "");
			lbLotacao.setValue(viatura.getLotacao() + "");
			lbConjuntoTrans.setValue(viatura.getConjuntoTransmissao());
			lbChassi.setValue(viatura.getChassi());
			lbPreco.setValue(viatura.getPreco() + "");
			lbTransmissao.setValue(viatura.getTransmissao());
			lbCombustivel.setValue(viatura.getCombustivel());
			lbCilindrada.setValue(viatura.getCilindrada() + "");
			lbNrPortas.setValue(viatura.getNrPortas() + "");
			lbCor.setValue(viatura.getCor());
			lbMatricula.setValue(viatura.getMatricula());
			lbParque.setValue(viatura.getParque().getNome());

			div_fotos.setVisible(true);

			imgFoto.setContent(imageBuffered(viatura.getImagens(), 0));
			imgFoto1.setContent(imageBuffered(viatura.getImagens(), 1));
			imgFoto2.setContent(imageBuffered(viatura.getImagens(), 2));
			imgFoto3.setContent(imageBuffered(viatura.getImagens(), 3));
			imgFoto4.setContent(imageBuffered(viatura.getImagens(), 4));
		}
	}

	@Listen("onClick =#btPesqViatura")
	public void onClickPesquisa() {

		String paramentro = "";
		if (!(cbMarca.getSelectedIndex() == -1))
			paramentro = paramentro + " and modelo.marca.descMarca like '" + cbMarca.getText() + "'";

		if (!(cbModelo.getSelectedIndex() == -1))
			paramentro = paramentro + " and modelo.descModelo like '" + cbModelo.getText() + "'";

		if (!(cbTransmissao.getSelectedIndex() == -1))
			paramentro = paramentro + " and transmissao like '" + cbTransmissao.getText() + "'";

		if (!(cbCombustivel.getSelectedIndex() == -1))
			paramentro = paramentro + " and combustivel like '" + cbCombustivel.getText() + "'";

		if (!(cbConjuntoTrans.getSelectedIndex() == -1))
			paramentro = paramentro + " and conjuntoTransmissao like '" + cbConjuntoTrans.getText() + "'";

		if (!(cbCor.getSelectedIndex() == -1))
			paramentro = paramentro + " and cor like '" + cbCor.getText() + "'";

		if (!(cbParque.getSelectedIndex() == -1))
			paramentro = paramentro + " and parque.nome like '" + cbParque.getText() + "'";

		/*
		 * List<Viatura> viaturas = viaturaDAO(Viatura.class).findViatura(
		 * cbAnoMin.getText() != "" ? Integer.valueOf(cbAnoMin.getText()) : 1950,
		 * cbAnoMax.getText() != "" ? Integer.valueOf(cbAnoMax.getText()) :
		 * DateUtil.currentYear(), cbCilindradaMin.getText() != "" ?
		 * Double.valueOf(cbCilindradaMin.getText()) : 700, cbCilindradaMax.getText() !=
		 * "" ? Double.valueOf(cbCilindradaMax.getText()) : 4000, cbLotacaoMin.getText()
		 * != "" ? Integer.valueOf(cbLotacaoMin.getText()) : 2, cbLotacaoMax.getText()
		 * != "" ? Integer.valueOf(cbLotacaoMax.getText()) : 100, cbPrecoMin.getText()
		 * != "" ? Double.valueOf(cbPrecoMin.getText()) : 80000, cbPrecoMax.getText() !=
		 * "" ? Double.valueOf(cbPrecoMax.getText()) : 2000000, cbQuiloMin.getText() !=
		 * "" ? Long.valueOf(cbQuiloMin.getText()) : 0, cbQuiloMax.getText() != "" ?
		 * Long.valueOf(cbQuiloMax.getText()) : 200000, paramentro);
		 */
		// preencheListBox(viaturas, listViaturas);
	}

	@Listen("onCreate = #winListaPrincipal")
	public void onCreateListaPrincipal() {
		preencheListBox(viaturaDAO.findAll(Viatura.class), listViaturas);
	}

	@Listen("onSelect = #cbMarca")
	public void preencheModelo() {
		cbModelo.setSelectedIndex(-1);
		List<Modelo> modelos = modeloDAO.findAll(Modelo.class);
		List<Modelo> lista = new ArrayList<Modelo>();
		for (Modelo modelo : modelos) {
			if (modelo.getMarca().getId() == ((Marca) cbMarca.getSelectedItem().getValue()).getId()) {
				lista.add(modelo);
			}
		}
		preencheComboBox(lista, cbModelo);
	}

	public void preencheModelo(Marca marca) {
		List<Modelo> modelos = modeloDAO.findAll(Modelo.class);
		List<Modelo> lista = new ArrayList<Modelo>();
		for (Modelo modelo : modelos) {
			if (modelo.getMarca().getId() == marca.getId()) {
				lista.add(modelo);
			}
		}
		preencheComboBox(lista, cbModelo);
	}

	@Listen("onClick = #btGravar")
	public void save() throws Exception {
		List<Imagem> imagens = new ArrayList<>();
		if (btGravar.getLabel().equals("Actualizar")) {
			Viatura viatura = (Viatura) session.getAttribute("viatura");
			if (verificaCampos()) {
				viatura.setModelo((Modelo) getSelectedItem(cbModelo));
				viatura.setTransmissao(cbTransmissao.getValue());
				viatura.setCombustivel(cbCombustivel.getValue());
				viatura.setCilindrada(dbCilindrada.getValue());
				viatura.setLotacao(ibLotacao.getValue());
				viatura.setNrPortas(ibNrPortas.getValue());
				viatura.setConjuntoTransmissao(cbConjuntoTrans.getValue());
				viatura.setChassi(tbChassi.getValue());
				viatura.setMatricula(tbMatricula.getValue());
				viatura.setCor(cbCor.getSelectedItem().getValue());
				viatura.setPreco(dbPreco.getValue());
				viatura.setParque((Parque) getSelectedItem(cbParque));
				viatura.setAnoPub(ibAno.getValue());
				viatura.setQuilometragem(lbQuilometragem.getValue());
				viatura.setTipoCarroceira(cbTipoCarroceira.getSelectedItem().getValue());
				viaturaDAO.save(viatura);
				printInf("Updated " + viatura.toString());
				mensagem("Actualizado");

				listViaturas.notifyAll();
				limpaCampos();
			}

		} else {
			printInf("Entrando no m�todo save");

			Viatura viatura = new Viatura();

			if (verificaCampos()) {

				for (int i = 0; i < 5; i++) {
					if (bytes[i] != null) {
						Imagem imagem = new Imagem();
						imagem.setBs(bytes[i]);
						imagem.setDescImagem(i + "_" + ((Marca) getSelectedItem(cbMarca)).getDescMarca() + "_"
								+ tbMatricula.getValue());
						imagem.setSize(bytes[i].length);

						imagemDAO.save(imagem);
						imagens.add(imagem);
					}
				}
				viatura.setModelo((Modelo) getSelectedItem(cbModelo));
				viatura.setTransmissao(cbTransmissao.getValue());
				viatura.setCombustivel(cbCombustivel.getValue());
				viatura.setCilindrada(dbCilindrada.getValue());
				viatura.setLotacao(ibLotacao.getValue());
				viatura.setNrPortas(ibNrPortas.getValue());
				viatura.setConjuntoTransmissao(cbConjuntoTrans.getValue());
				viatura.setChassi(tbChassi.getValue());
				viatura.setMatricula(tbMatricula.getValue());
				viatura.setCor(cbCor.getSelectedItem().getValue());
				viatura.setPreco(dbPreco.getValue());
				viatura.setParque((Parque) getSelectedItem(cbParque));
				viatura.setAnoPub(ibAno.getValue());
				viatura.setQuilometragem(lbQuilometragem.getValue());
				viatura.setTipoCarroceira(cbTipoCarroceira.getSelectedItem().getValue());
				viatura.setImagens(imagens);

				viaturaDAO.save(viatura);
				printInf("saved" + viatura.toString());
				mensagem("Gravado");
				limpaCampos();
			}
		}
	}

	@Listen("onOK =  #bandPesquisa; onOpen=#bandPesquisa")
	public void bandPesquisa() throws IOException { // M�TODO PARA PESQUISA
													// PERSONALIZADA NA LISTA
		//preencheListBox(viaturaDAO.findViatura(bandPesquisa.getText()), listViaturas);
	}

	@Listen("onUpload = #btAddFoto")
	public void uploadFoto(UploadEvent e) throws IOException {
		int i = 1;
		for (Media m : e.getMedias()) {
			if (i < e.getMedias().length) {
				if (m.isBinary()) {

					lstBytes.add(i, new byte[m.getStreamData().available()]);

					m.getStreamData().read(lstBytes.get(i), 0, m.getStreamData().available());
				} else {
					lstBytes.add(m.getStringData().getBytes("UTF-8"));
				}

				i++;
			}
		}

		// for (Media m : e.getMedias()) {
		//
		// if (i < 5) {
		// if (m.isBinary()) {
		// bytes[i] = new byte[m.getStreamData().available()];
		// m.getStreamData().read(bytes[i], 0, m.getStreamData().available());
		// } else {
		// bytes[i] = m.getStringData().getBytes("UTF-8");
		// }
		// if (bytes[i] != null) {
		// ByteArrayInputStream byteArrayInputStream = new
		// ByteArrayInputStream(bytes[i]);
		// bufferedImages[i] = ImageIO.read(byteArrayInputStream);
		// }
		// div_fotos.setVisible(true);
		// i++;
		// } else
		// mensagemWarning("N�o pode adicionar mais de 5 imagens", btAddFoto);
		// }
		// if (bufferedImages[0] != null)
		// imgFoto.setContent(bufferedImages[0]);
		// if (bufferedImages[1] != null)
		// imgFoto1.setContent(bufferedImages[1]);
		// if (bufferedImages[2] != null)
		// imgFoto2.setContent(bufferedImages[2]);
		// if (bufferedImages[3] != null)
		// imgFoto3.setContent(bufferedImages[3]);
		// if (bufferedImages[4] != null)
		// imgFoto4.setContent(bufferedImages[4]);
	}

	@Listen("onDetail=#listViaturas")
	public void onDetail(ForwardEvent evt) {
		Image img = (Image) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) img.getParent().getParent();
		Viatura obj = litem.getValue();
		session.setAttribute("viatura", obj);
		createPopUp("/viatura/pages/DetalhesViatura.zul", "80%", "80%", true, divRegViatura);
		limpaCampos();
	}

	@Listen("onEdit=#listViaturas")
	public void onEdit(ForwardEvent evt) throws IOException {
		Image img = (Image) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) img.getParent().getParent();
		Viatura obj = litem.getValue();
		session.setAttribute("viatura", obj);
		fillFields(obj);
		btGravar.setLabel("Actualizar");
		btGravar.setImage("/viatura/images/ic_edit.png");
		btCancelar.setVisible(true);
	}

	public void habilitaCampos() {
		cbMarca.setDisabled(false);
		cbModelo.setDisabled(false);
		ibAno.setDisabled(false);
		ibLotacao.setDisabled(false);
		cbTransmissao.setDisabled(false);
		tbChassi.setDisabled(false);
		dbPreco.setDisabled(false);
		cbConjuntoTrans.setDisabled(false);
		cbCombustivel.setDisabled(false);
		dbCilindrada.setDisabled(false);
		ibNrPortas.setDisabled(false);
		cbCor.setDisabled(false);
		tbMatricula.setDisabled(false);
		cbParque.setDisabled(false);
	}

	public void desabilitaCampos() {
		cbMarca.setDisabled(true);
		cbModelo.setDisabled(true);
		ibAno.setDisabled(true);
		ibLotacao.setDisabled(true);
		cbTransmissao.setDisabled(true);
		tbChassi.setDisabled(true);
		dbPreco.setDisabled(true);
		cbConjuntoTrans.setDisabled(true);
		cbCombustivel.setDisabled(true);
		dbCilindrada.setDisabled(true);
		ibNrPortas.setDisabled(true);
		cbCor.setDisabled(true);
		tbMatricula.setDisabled(true);
		cbParque.setDisabled(true);
	}

	public boolean verificaMatricula() {
		List<Viatura> viaturas = viaturaDAO.findAll(Viatura.class);
		for (Viatura viatura : viaturas) {
			if (viatura.getMatricula().equals(tbMatricula.getText()))
				return false;
		}
		return true;
	}

	@Listen("onClick=#btCancelar")
	public void limpaCampos() {
		cbMarca.setSelectedIndex(-1);
		cbModelo.setSelectedIndex(-1);
		ibAno.setText("");
		ibLotacao.setText("");
		cbTransmissao.setSelectedIndex(-1);
		tbChassi.setText("");
		dbPreco.setText("");
		cbConjuntoTrans.setSelectedIndex(-1);
		cbCombustivel.setSelectedIndex(-1);
		dbCilindrada.setText("");
		ibNrPortas.setText("");
		cbCor.setSelectedIndex(-1);
		tbMatricula.setText("");
		cbParque.setSelectedIndex(-1);
		div_fotos.setVisible(false);
		lbQuilometragem.setText("");

		cbTipoCarroceira.setSelectedIndex(-1);

		btGravar.setLabel("Gravar");
		btGravar.setImage("/viatura/images/ic_diskette.png");
		btCancelar.setVisible(false);
	}

	public void fillFields(Viatura viatura) throws IOException {
		cbMarca.setValue(viatura.getModelo().getMarca().getDescMarca());
		preencheModelo(viatura.getModelo().getMarca());
		cbModelo.setValue(viatura.getModelo().getDescModelo());
		ibAno.setValue(viatura.getAnoPub());
		ibLotacao.setText(viatura.getLotacao() + "");
		cbTransmissao.setValue(viatura.getTransmissao());
		tbChassi.setText(viatura.getChassi());
		dbPreco.setText(viatura.getPreco() + "");
		cbConjuntoTrans.setValue(viatura.getConjuntoTransmissao());
		cbCombustivel.setValue(viatura.getCombustivel());
		dbCilindrada.setText(viatura.getCilindrada() + "");
		ibNrPortas.setText(viatura.getNrPortas() + "");
		cbCor.setValue(viatura.getCor());
		tbMatricula.setText(viatura.getMatricula());
		cbParque.setValue(viatura.getParque().getNome());
		lbQuilometragem.setValue(viatura.getQuilometragem());
		cbTipoCarroceira.setValue(viatura.getTipoCarroceira());

		div_fotos.setVisible(true);

		// if (viatura.getImagens() != null) {
		// for (int i = 1; i <= viatura.getImagens().size(); i++) {
		// lstImage.get(i).setContent(imageBuffered(viatura.getImagens(), i));
		// }
		// }

	}

	public boolean verificaCampos() {
		boolean a = true;
		if (cbMarca.getSelectedIndex() == -1) {
			cbMarca.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (cbModelo.getSelectedIndex() == -1) {
			cbModelo.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (cbTransmissao.getSelectedIndex() == -1) {
			cbTransmissao.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (cbConjuntoTrans.getSelectedIndex() == -1) {
			cbConjuntoTrans.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (cbCombustivel.getSelectedIndex() == -1) {
			cbCombustivel.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (cbCor.getSelectedIndex() == -1) {
			cbCor.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (cbParque.getSelectedIndex() == -1) {
			cbParque.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (ibAno.getText() == "") {
			ibAno.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (ibLotacao.getText() == "") {
			ibLotacao.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (tbChassi.getText() == "") {
			tbChassi.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (dbPreco.getText() == "") {
			dbPreco.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (dbCilindrada.getText() == "") {
			dbCilindrada.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}
		if (ibNrPortas.getText() == "") {
			ibNrPortas.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}

		if (tbMatricula.getText() == "") {
			tbMatricula.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}

		if (lbQuilometragem.getText() == "") {
			lbQuilometragem.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}

		if (!verificaMatricula()) {
			tbMatricula.setErrorMessage("Esta Matricula j� existe");
			a = false;
		}

		if (cbTipoCarroceira.getSelectedIndex() == -1) {
			cbTipoCarroceira.setErrorMessage("Campo Obrigat�rio");
			a = false;
		}

		return a;
	}

	/*@Listen("onClick=#tbbToyota")
	public void onClicktbbToyota() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Toyota"), listViaturas);
	}

	@Listen("onClick=#tbbNissan")
	public void onClicktbbNissan() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Nissan"), listViaturas);
	}

	@Listen("onClick=#tbbHonda")
	public void onClicktbbHonda() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Honda"), listViaturas);
	}

	@Listen("onClick=#tbbMitsubishi")
	public void onClicktbbMitsubishi() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Mitsubishi"), listViaturas);
	}

	@Listen("onClick=#tbbMercedes")
	public void onClicktbbMercedes() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Mercedes-Benz"), listViaturas);
	}

	@Listen("onClick=#tbbBmw")
	public void onClicktbbBmw() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("BMW"), listViaturas);
	}

	@Listen("onClick=#tbbMazda")
	public void onClicktbbMazda() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Mazda"), listViaturas);
	}

	@Listen("onClick=#tbbSubaru")
	public void onClicktbbSubaru() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Subaru"), listViaturas);
	}

	@Listen("onClick=#tbbVW")
	public void onClicktbbVW() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("VolksWagen"), listViaturas);
	}

	@Listen("onClick=#tbbSuzuki")
	public void onClicktbbSuzuki() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Suzuki"), listViaturas);
	}

	@Listen("onClick=#tbbLandRover")
	public void onClicktbbLandRover() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Land Rover"), listViaturas);
	}

	@Listen("onClick=#tbbIsuzu")
	public void onClicktbbIsuzu() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Isuzu"), listViaturas);
	}

	@Listen("onClick=#tbbAudi")
	public void onClicktbbAudi() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Audi"), listViaturas);
	}

	@Listen("onClick=#tbbFord")
	public void onClicktbbFord() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Ford"), listViaturas);
	}

	@Listen("onClick=#tbbDaihatsu")
	public void onClicktbbDaihatsu() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Daihatsu"), listViaturas);
	}

	@Listen("onClick=#tbbLexus")
	public void onClicktbbLexus() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Lexus"), listViaturas);
	}

	@Listen("onClick=#tbbKia")
	public void onClicktbbKIA() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("KIA"), listViaturas);
	}

	@Listen("onClick=#tbbHino")
	public void onClicktbbHino() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Hino"), listViaturas);
	}

	@Listen("onClick=#tbbPeugeot")
	public void onClicktbbPeugeot() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Peugeot"), listViaturas);
	}

	@Listen("onClick=#tbbVolvo")
	public void onClicktbbVolvo() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Volvo"), listViaturas);
	}

	@Listen("onClick=#tbbJaguar")
	public void onClicktbbJaguar() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Jaguar"), listViaturas);
	}

	@Listen("onClick=#tbbJeep")
	public void onClicktbbJeep() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Jeep"), listViaturas);
	}

	@Listen("onClick=#tbbSsangyong")
	public void onClicktbbSsangyong() {
		preencheListBox(new ViaturaDAO().findViaturaByMarca("Ssangyong"), listViaturas);
	}*/
}