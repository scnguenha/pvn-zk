
package mz.co.scn.pvn.controller;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;

/**
 * @Author Sidónio Goenha Dec 6, 2017
 **/
public class NaveBarViewModel {
	private String desc = "Teste";

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@NotifyChange("selected")
	@Command
	public void clickToolBar(Event ev) {

		Clients.showNotification(getDesc(), null, null, null, 2000);
	}

}
