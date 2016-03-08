package event;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FermetureFenetre extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("Fermeture fenêtre");
		System.exit(0);
	}

}
