package dragndrop;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DragFont extends JFrame {
	
	protected JLabel label = new JLabel("ihm");
	protected JFormattedTextField text1 = new JFormattedTextField();
	protected JFormattedTextField text2 = new JFormattedTextField();
	protected LayoutManager layout;
	
	
	{
		this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		// JLabel
		this.add(this.label);
		this.label.setTransferHandler(new TransferHandler("font"));
		this.label.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// Empty
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				label.getTransferHandler().exportAsDrag(label, e, TransferHandler.COPY);
				
			}
		});
		
		
		// JFormattedTextField 1
		text1.setDragEnabled(true);
		text1.setFont(new Font("liberation", Font.BOLD | Font.ITALIC, 17));
		this.add(this.text1);
		
		
		// JFormattedTextField 2
		text2.setDragEnabled(true);
		this.add(this.text2);
		
		
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(300, 100);
	}
	
	
	public static void main(String[] args) {
		DragFont window = new DragFont();
		window.setVisible(true);
	}
	
}
