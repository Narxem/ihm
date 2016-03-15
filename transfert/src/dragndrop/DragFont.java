package dragndrop;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DragFont extends JFrame {
	
	protected JLabel label = new JLabel("ihm");
	protected JFormattedTextField text1 = new JFormattedTextField();
	protected JFormattedTextField text2 = new JFormattedTextField();
	protected LayoutManager layout;
	
	
	{
		this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		this.add(this.label);
		this.add(this.text1);
		this.add(this.text2);
		text1.setDragEnabled(true);
		text2.setDragEnabled(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 100);
	}
	
	
	public static void main(String[] args) {
		DragFont window = new DragFont();
		window.setVisible(true);
	}
	
}
