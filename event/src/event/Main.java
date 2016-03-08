package event;

import javax.swing.JFrame;


public class Main {

	public static void main(String args[]) {
		JFrame frame=  new JFrame();
		ArdoiseMagique window = new ArdoiseMagique();
		frame.add(window);
		frame.setVisible(true);
	}
}
