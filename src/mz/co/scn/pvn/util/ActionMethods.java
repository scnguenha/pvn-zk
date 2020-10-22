package mz.co.scn.pvn.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import mz.co.scn.pvn.model.Imagem;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * @author Sidónio Goenha
 *
 * @param <T>
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ActionMethods<T> extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	protected HttpSession session = (HttpSession) Sessions.getCurrent().getNativeSession();

	public void mensagemError(String msg, Component ref) {
		Clients.showNotification(msg, Clients.NOTIFICATION_TYPE_ERROR, ref, null, 2000);
	}

	public void mensagemInformation(String msg, Component ref) {
		Clients.showNotification(msg, Clients.NOTIFICATION_TYPE_INFO, ref, null, 2000);
	}

	public void mensagemWarning(String msg, Component ref) {
		Clients.showNotification(msg, Clients.NOTIFICATION_TYPE_WARNING, ref, null, 2000);
	}

	public void mensagem(String msg) {
		Clients.showNotification(msg, null, null, null, 2000);
	}

	public void printInf(String msg) {
		System.out.println(msg);
	}

	/**
	 * 
	 * @param lista
	 * @param nameReport
	 * @param namePage
	 * @param title
	 * @throws JRException
	 * @throws FileNotFoundException
	 */
	public void jasperPDF(List<T> lista, String nameReport, String namePage, String title)
			throws JRException, FileNotFoundException {

		JasperPrint print = JasperFillManager.fillReport(
				Sessions.getCurrent().getWebApp().getRealPath("/jasper/" + nameReport + ".jasper"), null,
				new JRBeanCollectionDataSource(lista));

		byte[] bytes = JasperExportManager.exportReportToPdf(print);

		AMedia aMedia = new AMedia(title, "pdf", "application/pdf", bytes);

		Sessions.getCurrent().setAttribute("aMedia", aMedia);

		createPopUp("/apresentacao/relatorioView/" + namePage + ".zul", "70%", "100%", true, null);
	}

	/**
	 * 
	 * @param urlPage
	 * @param width
	 * @param height
	 * @param closable
	 * @param component
	 * @return
	 */
	protected Window createPopUp(String urlPage, String width, String height, boolean closable, Component component) {
		Window w = (Window) Executions.createComponents(urlPage, component, null);
		w.setPosition("center");
		w.setMinimizable(false);
		w.setClosable(closable);
		w.setWidth(width);
		w.setHeight(height);
		w.doModal();
		return w;
	}

	/**
	 * Este m�todo serve para preencher uma listBox de acordo com os parametros
	 * passados
	 * 
	 * @param lista
	 * @param listbox
	 * @throws IOException
	 */
	protected void preencheListBox(List<T> lista, Listbox listbox) {
		listbox.setModel(new ListModelList<T>(lista));
	}

	/**
	 * return image with the given id
	 * 
	 * @param parent
	 * @return Image
	 */
	protected Image getImageByComponentAndId(Component parent) {
		List<Image> images = new ArrayList<Image>();

		for (Component component : Selectors.find(parent, "#image")) {
			if (component instanceof Image)
				images.add((Image) component);
		}

		return images.get(0);
	}

	/**
	 * Este metodo serve para preencher uma comboBox de acordo com os parametros
	 * passados
	 * 
	 * @param lista
	 * @param comboBox
	 */
	protected void preencheComboBox(List<T> lista, Combobox comboBox) {
		comboBox.setModel(new ListModelList<T>(lista));
	}

	/**
	 * Este metodo serve para preencher uma comboBox de acordo com os parametros
	 * passados
	 * 
	 * @param comboBox
	 * @param lista
	 */
	protected void preencheComboBox(Combobox comboBox, List<String> lista) {
		comboBox.setModel(new ListModelList<String>(lista));
	}

	/**
	 * Este m�todo serve para preencher uma comboBox de acordo com os parametros
	 * passados
	 * 
	 * @param entity
	 * @param comboBox
	 * @param lista
	 */
	protected void preencheComboBox(T entity, Component comboBox, List<T> lista) {
		if (lista != null) {
			for (T object : lista) {
				Comboitem item = new Comboitem(object.toString());
				item.setValue(object);
				comboBox.appendChild(item);
			}
		}
	}

	protected void preencheComboBoxWithAno(Combobox comboBox, int anoMin) {
		List<Integer> lista = new ArrayList<>();
		for (int i = DateUtil.currentYear(); i >= anoMin; i--) {
			lista.add(i);
		}
		comboBox.setModel(new ListModelList<Integer>(lista));

	}

	protected void preencheComboBoxWithNumber(Combobox comboBox, int numMin, int numMax, int increment) {
		List<Integer> lista = new ArrayList<>();
		for (int i = numMin; i <= numMax; i += increment) {
			lista.add(i);
		}
		comboBox.setModel(new ListModelList<Integer>(lista));

	}

	protected Button findButtonById(String id) {
		Button b = new Button();
		return b;
	}

	/**
	 * Este m�todo retorna um objecto selecionado na combobox � necess�rio ao
	 * recebe-lo fazer cast
	 * 
	 * @param combobox
	 * @return
	 */
	protected Object getSelectedItem(Combobox combobox) {
		return combobox.getSelectedItem().getValue();
	}

	/**
	 * 
	 * @param combobox
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected T getSelectedItem(Combobox combobox, T t) {
		return (T) combobox.getSelectedItem().getValue();
	}

	protected Component findComponentById() {
		return null;
	}

	/**
	 * 
	 * @param images
	 * @param pos
	 * @return
	 * @throws IOException
	 */
	protected BufferedImage imageBuffered(List<Imagem> images, int pos) throws IOException {
		if (pos <= images.size()) {
			if (images.get(pos) != null) {
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(images.get(pos).getBs());
				BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
				return bufferedImage;
			}
		}
		return null;
	}

	protected BufferedImage imageBuffered(byte[] bs) throws IOException {
		if (bs != null) {
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs);
			BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
			return bufferedImage;
		}

		return null;
	}

	protected BufferedImage imageBuffered(byte[][] bytes, int pos) throws IOException {
		if (pos <= bytes.length) {
			if (bytes[pos] != null) {
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes[pos]);
				BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);
				return bufferedImage;
			}
		}
		return null;
	}

}
