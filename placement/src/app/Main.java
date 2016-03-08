package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

public class Main {

	static JFrame window;
	
	static {
		window = new JFrame();
		window.setSize(400, 400);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void mainComponent(String args[]) {
		window.getContentPane().add(new JLabel("Hello World !"));
		JDialog dialog = new JDialog(window);
		dialog.setModal(false);
		window.setVisible(true);
		dialog.setVisible(true);
	}
	
	// Question 6
	public static void mainBorderLayout(String args[]) {
		window.add(new JButton("top"), BorderLayout.NORTH);
		window.add(new JButton("left"), BorderLayout.WEST);
		window.add(new JButton("right"), BorderLayout.EAST);
		window.add(new JButton("center"), BorderLayout.CENTER);
		window.add(new JButton("bottom"), BorderLayout.SOUTH);
		window.setVisible(true);
	}
	
	// Question 7
	public static void mainFlowLayout(String args[]) {
		window.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
		for (int i = 0; i < 16; i++) {
			window.add(new JButton("button" + i));
		}
		window.setVisible(true);
	}
	
	// Question 8
	public static void mainGridLayout(String args[]) {
		window.getContentPane().setLayout(new GridLayout(4, 4));
		for (int i = 0; i < 16; i++) {
			window.add(new JButton("button" + i));
		}
		window.setVisible(true);
	}
	
	// Question 9
	public static void main(String args[]) {
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));
		window.add(new JButton("bouton 1"));
		window.add(new JButton("bouton 2"));
		window.add(Box.createRigidArea(new Dimension(50, 50)));
		window.add(new JButton("bouton 3"));
		
		window.setVisible(true);
	}

}
