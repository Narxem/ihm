package actions;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */
public class UndoGUI {
	private DefaultListModel<String> listModel;
	private JTextField text;
	private JList list;
	
	UndoGUI() {
		// JFrame
		JFrame fen = new JFrame("Undo/Redo");
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ActionListener remove = new RemoveItem();
		ActionListener add = new AddItem();
		ActionListener undo = new Undo();
		ActionListener redo = new Redo();
	
		
		// Liste
		listModel = new DefaultListModel<String>();
		listModel.addElement("Universite Lille 1");
		listModel.addElement("Master informatique");
		listModel.addElement("IHM");
		list = new JList(listModel);
		JScrollPane listScroller = new JScrollPane(list);
		text = new JTextField();
		text.addActionListener(add);
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.addActionListener(add);
		
	
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Edition");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Supprimer");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		menuItem.addActionListener(remove);
		menu.add(menuItem);
		menuItem = new JMenuItem("Annuler");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		menuItem.addActionListener(undo);
		menu.add(menuItem);
		menuItem = new JMenuItem("Refaire");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK + ActionEvent.SHIFT_MASK));
		menuItem.addActionListener(redo);
		menu.add(menuItem);
		
		// ToolBar
		JToolBar toolBar = new JToolBar("Barre d'outils");
		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.addActionListener(remove);
		
		JButton boutonAnnuler = new JButton("Annuler");
		boutonAnnuler.setEnabled(false);
		JButton boutonRefaire = new JButton("Refaire");
		boutonRefaire.setEnabled(false);
		toolBar.add(boutonSupprimer);
		toolBar.add(boutonAnnuler);
		toolBar.add(boutonRefaire);
		
		fen.setJMenuBar(menuBar);
		fen.add(toolBar);
		fen.getContentPane().setLayout(new BoxLayout(fen.getContentPane(),BoxLayout.Y_AXIS));
		fen.getContentPane().add(listScroller);
		fen.getContentPane().add(text);
		fen.getContentPane().add(boutonAjouter);
		fen.pack();
		fen.setVisible(true);		
	}
	
	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			new UndoGUI();
		    }
		});
	}
	
	private class AddItem implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (text.getText().length() > 0) {
				listModel.addElement(text.getText());
				text.setText("");
			} 
		}
	}
	
	private class RemoveItem implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		    int index = list.getSelectedIndex();
		    if (index != -1) listModel.remove(index);
		}
	}
	
	private class Undo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Undo");
			
		}
	}
	
	private class Redo implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Redo");
			
		}
		
	}

}
