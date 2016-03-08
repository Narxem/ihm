package event;
/**
 * ArdoiseMagique.java
 *
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 * @version
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

class Point {
	public Integer x,y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
}

class Curve {
	public ArrayList<Point> points;

	Curve() {
		points = new ArrayList<Point>();
	}

	public void addPoint(Point P) {
		points.add(P);
	}

	public void clear() {
		points.clear();
	}
}

public class ArdoiseMagique extends JPanel {
	private ArrayList<Curve> curves;

	public ArdoiseMagique(){
		curves = new ArrayList<Curve>();
		curves.add(new Curve());
		setBackground(Color.white);
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				newCurve();
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				addPoint(e.getX(), e.getY());
			}
		});
	}

	public void addPoint(Integer x, Integer y) {
		curves.get(curves.size()-1).addPoint(new Point(x,y));
		repaint();
	}

	public void newCurve() {
		curves.add(new Curve());
	}

	public void clear() {
		curves.clear();
		curves.add(new Curve());
		repaint();
	}

	public void paintComponent(Graphics g) {
		Point Pprev, Pcurrent;
		super.paintComponent(g);

		Iterator<Curve> itcurve = curves.iterator();

		Pprev = new Point();

		// Pour chaque courbe
		while (itcurve.hasNext()) {
			Iterator<Point> it = itcurve.next().points.iterator();

			if (it.hasNext()) {
				Pprev = it.next();
			}

			// Dessine les points d'une courbe
			while (it.hasNext()) {
				Pcurrent = it.next();
				g.drawLine(Pprev.x,Pprev.y, Pcurrent.x, Pcurrent.y);
				Pprev = Pcurrent;
			}
		}
	}
}