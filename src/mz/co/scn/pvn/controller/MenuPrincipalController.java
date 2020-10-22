package mz.co.scn.pvn.controller;

import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;

import mz.co.scn.pvn.util.ActionMethods;

public class MenuPrincipalController extends ActionMethods {

	@Wire
	private Include contentInclude;

	@Wire
	private Image imgReload;

	@Wire
	private Label lbReload;

	private Map<String, String> pageMap;

	private Menubar menubar;
	private Menu menu_registo, menu_lista;
	private Menuitem menu_reg_cliente, menu_reg_viatura;
	private Div divContentInclude, divMenuPrincipal;

	@Listen("onCreate = #divMenuPrincipal")
	public void onCreate() {
		if ((String) session.getAttribute("lastPath") != null)
			contentInclude.setSrc((String) session.getAttribute("lastPath"));
		else
			contentInclude.setSrc("/viatura/pages/menu_principal.zul");
	}

	@Listen("onClick = #menu_reg_cliente; onClick = #cardCliente")
	public void onSelectMenuCliente() {
		contentInclude.setSrc("/viatura/pages/registo_cliente.zul");
	}

	@Listen("onClick = #menu_reg_viatura; onClick = #cardViatura")
	public void onSelectMenuViatura() {
		contentInclude.setSrc("/viatura/pages/RegistoViatura.zul");
	}

	@Listen("onClick = #imgLogo")
	public void onClickLogo() {
		contentInclude.setSrc("/viatura/pages/menu_principal.zul");
	}

	@Listen("onClick = #lbReload; onClick =#imgReload")
	public void onClickReload() {
		Executions.sendRedirect("/viatura/menu/menu_principal.zul");
	}

}
