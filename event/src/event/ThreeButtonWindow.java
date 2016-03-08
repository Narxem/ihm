package event;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ThreeButtonWindow extends JFrame {
	private static final long serialVersionUID = 3626252008778052810L;

	private JLabel label;
	protected JButton button1;
	protected JButton button2;
	protected JButton button3;

	protected LayoutManager layout;

	public ThreeButtonWindow() {

		button1 = new JButton("Bouton 1");
		button2 = new JButton("Bouton 2");
		button3 = new JButton("Bouton 3");
		label = new JLabel("Nothing");
		layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);

		this.setLayout(layout);
		

		this.getContentPane().add(label);
		this.getContentPane().add(button1);
		this.getContentPane().add(button2);
		this.getContentPane().add(button3);
		this.pack();

		button1.addActionListener(new ReponseAuClic());
		button2.addActionListener(new ReponseAuClic());
		button3.addActionListener(new ReponseAuClic());
		
		this.addWindowListener(new FermetureFenetre());

	}
	
	
	private class ReponseAuClic implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			label.setText(button.getText());

		}
	}
}
