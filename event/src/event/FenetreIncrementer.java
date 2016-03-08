package event;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreIncrementer extends JFrame {

	private static final long serialVersionUID = 3626252008778052810L;

	private JLabel label;
	protected JButton button;

	protected BorderLayout layout;


	public FenetreIncrementer() {

		button = new JButton("Incrémenter");
		label = new JLabel("0");
		layout = new BorderLayout();

		this.setLayout(layout);
		

		this.getContentPane().add(label, BorderLayout.CENTER);
		this.getContentPane().add(button, BorderLayout.SOUTH);
		this.pack();

		button.addActionListener(new ReponseAuClic());
		
		this.addWindowListener(new FermetureFenetre());
		/*
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				label.setText((Integer.parseInt(label.getText()) + 1) + "");
				System.out.println("Clic sur le bouton");
			}
		}); */

	}
	
	
	private class ReponseAuClic implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			label.setText((Integer.parseInt(label.getText()) + 1) + "");
			System.out.println("Clic sur le bouton");

		}
	}

}
