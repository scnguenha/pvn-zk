package mz.co.scn.pvn.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

import mz.co.scn.pvn.dao.ClienteDAO;
import mz.co.scn.pvn.model.Cliente;
import mz.co.scn.pvn.util.ActionMethods;
import mz.co.scn.pvn.util.AppConstants;

/**
 * @author Sidónio Goenha
 *
 */
public class ClienteController extends ActionMethods<Cliente> {

	private static final long serialVersionUID = 4677572831030542239L;

	private Cliente cliente = new Cliente();

	@Wire
	private Textbox tbNome, tbApelido, tbMorada, tbEmail, tbTelemovel, tbPassword, tbPassword1, tbUsername;

	@Wire
	private Label lbNome, lbApelido, lbMorada, lbEmail, lbTelemovel, lbGenero, lbDataNascimento;

	@Wire
	private Radiogroup rgGenero;

	@Wire
	private Datebox dbDataNascimento;

	private byte[] image;
	@Wire
	private Image imgFotoCliente;

	@Wire
	private Listbox listClientes;
	@Wire
	private Button btCancelar, btGravar;

	@Wire
	private Div divRegCliente;

	@Wire
	private Bandbox bandPesquisa;

	@WireVariable
	private ClienteDAO clienteDAO;

	public Cliente getCliente() {
		return cliente;
	}

	@Listen("onCreate = #divRegCliente")
	public void onCreate() {
		preencherClientes();
		// session.setAttribute("lastPath", "/pvn/pages/RegistoCliente.zul");
	}

	public void preencherClientes() {
		/*
		 * List<Cliente> clientes = clienteDAO.findAll(Cliente.class);
		 * preencheListBox(clientes, listClientes);
		 */
	}

	@Listen("onOK =  #bandPesquisa; onOpen=#bandPesquisa")
	public void bandPesquisa() { // MÉTODO PARA PESQUISA PERSONALIZADA NA LISTA
		preencheListBox(clienteDAO.findClienteByKey(bandPesquisa.getText()), listClientes);
	}

	@Listen("onCreate= #winDetalhesCliente")
	public void onCreateDetalhes() throws IOException {
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		if (cliente != null) {
			lbNome.setValue(cliente.getNome());
			lbApelido.setValue(cliente.getApelido());
			lbDataNascimento.setValue(cliente.getData_nasc() + "");
			lbGenero.setValue(cliente.getGenero());
			lbTelemovel.setValue(cliente.getNrCelular());
			lbMorada.setValue(cliente.getMorada());
			lbEmail.setValue(cliente.getEmail());
			if (cliente.getFoto() != null)
				imgFotoCliente.setContent(imageBuffered(cliente.getFoto().getBs()));
		}
	}

	@Listen("onClick = #btGravar")
	public void save() throws Exception {
		/*
		 * if (btGravar.getLabel().equals("Gravar")) { if (verificaCampos()) { Cliente c
		 * = new Cliente();
		 * 
		 * if (image != null) { ImagemDAO imagemDAO = new ImagemDAO(); Imagem imagem =
		 * new Imagem(); imagem.setBs(image); imagem.setDescImagem( tbNome.getText() +
		 * "_" + tbApelido.getText() + "_" + dbDataNascimento.getValue());
		 * imagem.setSize(image.length); imagemDAO.save(imagem); c.setFoto(imagem); }
		 * c.setNome(tbNome.getText()); c.setApelido(tbApelido.getText());
		 * c.setData_nasc(dbDataNascimento.getValue());
		 * c.setGenero(rgGenero.getSelectedItem().getValue());
		 * c.setMorada(tbMorada.getText()); c.setEmail(tbEmail.getText());
		 * c.setNrCelular(tbTelemovel.getText());
		 * 
		 * clienteDAO.save(c);
		 * 
		 * // Utilizador utilizador = new Utilizador(); //
		 * utilizador.setUsername(tbUsername.getText()); //
		 * utilizador.setPassword(tbPassword.getText()); // utilizador.setCliente(c);
		 * 
		 * mensagemInformation("Gravado com Sucesso", null);
		 * 
		 * printInf("Saved {" + c.getNome() + " " + c.getApelido() + "}");
		 * 
		 * limpaCampos(); preencherClientes(); } } else { if (verificaCampos()) {
		 * 
		 * Cliente c = (Cliente) session.getAttribute("cliente"); Imagem imagem = null;
		 * if (c != null) { if (image != null) {
		 * 
		 * ImagemDAO imagemDAO = new ImagemDAO(); if (c.getFoto() != null) { imagem =
		 * c.getFoto(); imagem.setBs(image); imagem.setDescImagem( tbNome.getText() +
		 * "_" + tbApelido.getText() + "_" + dbDataNascimento.getValue());
		 * imagem.setSize(image.length); imagemDAO.update(imagem); } else { imagem = new
		 * Imagem(); imagem.setBs(image); imagem.setDescImagem( tbNome.getText() + "_" +
		 * tbApelido.getText() + "_" + dbDataNascimento.getValue());
		 * imagem.setSize(image.length); imagemDAO.save(imagem); c.setFoto(imagem);
		 * 
		 * }
		 * 
		 * } c.setNome(tbNome.getText()); c.setApelido(tbApelido.getText());
		 * c.setData_nasc(dbDataNascimento.getValue());
		 * c.setGenero(rgGenero.getSelectedItem().getValue());
		 * c.setMorada(tbMorada.getText()); c.setEmail(tbEmail.getText());
		 * c.setNrCelular(tbTelemovel.getText());
		 * 
		 * clienteDAO.update(c);
		 * 
		 * mensagemInformation("Actualizado com Sucesso", null);
		 * 
		 * printInf("Updated {" + c.getNome() + " " + c.getApelido() + "}");
		 * limpaCampos(); preencherClientes(); } } }
		 */
	}

	@Listen("onUpload = #btAddFotoCliente")
	public void uploadFoto(UploadEvent e) throws IOException {

		Media media = e.getMedia();

		if (media.isBinary()) {
			image = new byte[media.getStreamData().available()];
			media.getStreamData().read(image, 0, media.getStreamData().available());
		} else {
			image = media.getStringData().getBytes("UTF-8");
		}
		if (image != null) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(image);
			BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
			imgFotoCliente.setContent(bufferedImage);
		}
	}

	public boolean verificaCampos() {
		boolean a = true;
		if (tbNome.getText() == "") {
			tbNome.setErrorMessage("Campo Obrigatório");
			a = false;
		}
		if (!(tbNome.getText() == "") && !tbNome.getText().matches(AppConstants.PATTERN_NOME)) {
			tbNome.setErrorMessage("Nome Inválido");
			a = false;
		}
		if (!(tbApelido.getText() == "") && !tbApelido.getText().matches(AppConstants.PATTERN_NOME)) {
			tbApelido.setErrorMessage("Apelido Inválido");
			a = false;
		}
		if (tbApelido.getText() == "") {
			tbApelido.setErrorMessage("Campo Obrigatório");
			a = false;
		}

		if (dbDataNascimento.getText() == "") {
			dbDataNascimento.setErrorMessage("Campo Obrigatório");
			a = false;
		}

		if (tbMorada.getText() == "") {
			tbMorada.setErrorMessage("Campo Obrigatório");
			a = false;
		}

		if (!(tbEmail.getText() == "") && !tbEmail.getText().matches(AppConstants.PATTERN_EMAIL)) {
			tbEmail.setErrorMessage("Email Inválido");
			a = false;
		}

		// if (tbPassword.getText() == "") {
		// tbPassword.setErrorMessage("Campo Obrigatório");
		// a = false;
		// }
		//
		// if (tbPassword1.getText() == "") {
		// tbPassword1.setErrorMessage("Campo Obrigatório");
		// a = false;
		// }
		//
		// if (tbPassword.getText() != tbPassword1.getText()) {
		// tbPassword1.setText("");
		// tbPassword1.setErrorMessage("A senha deve ser a mesma");
		// a = false;
		// }

		return a;
	}

	@Listen("onClick=#btCancelar")
	public void limpaCampos() {
		tbNome.setText("");
		tbApelido.setText("");
		rgGenero.setSelectedIndex(0);
		dbDataNascimento.setText("");
		tbTelemovel.setText("");
		tbMorada.setText("");
		tbEmail.setText("");
		imgFotoCliente.setSrc("/pvn/images/userac.png");

		btCancelar.setVisible(false);
		btGravar.setLabel("Gravar");
		btGravar.setImage("/pvn/images/ic_diskette.png");
	}

	@Listen("onDetail=#listClientes")
	public void onDetail(ForwardEvent evt) {
		Image img = (Image) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) img.getParent().getParent();
		Cliente obj = litem.getValue();
		session.setAttribute("cliente", obj);
		createPopUp("/pvn/pages/DetalhesCliente.zul", "80%", "80%", true, divRegCliente);
		limpaCampos();
	}

	@Listen("onEdit=#listClientes")
	public void onEdit(ForwardEvent evt) throws IOException {
		Image img = (Image) evt.getOrigin().getTarget();
		Listitem litem = (Listitem) img.getParent().getParent();
		Cliente obj = litem.getValue();
		session.setAttribute("cliente", obj);
		fillFields(obj);

		btCancelar.setVisible(true);
		btGravar.setLabel("Actualizar");
		btGravar.setImage("/pvn/images/ic_edit.png");

	}

	public void fillFields(Cliente cliente) throws IOException {
		tbNome.setText(cliente.getNome());
		tbApelido.setText(cliente.getApelido());
		dbDataNascimento.setValue(cliente.getData_nasc());
		if (cliente.getGenero().equals("Masculino"))
			rgGenero.setSelectedIndex(0);
		else
			rgGenero.setSelectedIndex(1);

		tbTelemovel.setText(cliente.getNrCelular());
		tbMorada.setText(cliente.getMorada());
		tbEmail.setText(cliente.getEmail());
		if (cliente.getFoto() != null)
			imgFotoCliente.setContent(imageBuffered(cliente.getFoto().getBs()));
		else
			imgFotoCliente.setSrc("/pvn/images/userac.png");
	}

}
