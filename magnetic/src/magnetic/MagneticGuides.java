package magnetic;
import java.awt.Color ;
import java.awt.geom.Point2D ;

import javax.swing.JFrame ;

import fr.lri.swingstates.canvas.CExtensionalTag ;
import fr.lri.swingstates.canvas.CRectangle ;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.CShape ;
import fr.lri.swingstates.canvas.CStateMachine ;
import fr.lri.swingstates.canvas.Canvas ;
import fr.lri.swingstates.canvas.transitions.PressOnTag ;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Drag ;
import fr.lri.swingstates.sm.transitions.Press;
import fr.lri.swingstates.sm.transitions.Release ;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 *
 */
public class MagneticGuides extends JFrame {
	
	private static final long serialVersionUID = -3972339276338055557L;
	private Canvas canvas ;
	private CExtensionalTag oTag ;
	private CExtensionalTag hGuideTag ;
	private CExtensionalTag hTag;

	public MagneticGuides(String title, int width, int height) {
		super(title) ;
		canvas = new Canvas(width, height) ;
		canvas.setAntialiased(true) ;
		getContentPane().add(canvas) ;

		oTag = new CExtensionalTag(canvas) {} ;
		hGuideTag = new MagneticGuide(canvas);
		hTag = new MagneticGuide(canvas);

		@SuppressWarnings("unused")
		CStateMachine sm = new CStateMachine() {

			private Point2D p ;
			private CShape draggedShape ;

			public State start = new State() {
				Transition pressOnObject = new PressOnTag(oTag, BUTTON1, ">> oDrag") {
					public void action() {
						p = getPoint() ;
						draggedShape = getShape() ;
					}
				} ;
			
				// #####################################################################
				// Guides Creation
				Transition createHGuide = new Press(BUTTON1) {
					public void action() {
						p = getPoint() ;
						CSegment guide = canvas.newSegment(-1000, p.getY(), 1000, p.getY());
						guide.addTag(hTag);
					}
				};
				Transition pressOnHGuide = new PressOnTag(hTag, BUTTON1, ">> hDrag") {
					public void action() {
						p = getPoint();
						draggedShape = getShape();
					}
				};
			} ;

			
			
			public State oDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint() ;
						draggedShape.translateBy(q.getX() - p.getX(), q.getY() - p.getY()) ;
						p = q ;
					}
				} ;
				Transition release = new Release(BUTTON1, ">> start") {} ;
			} ;
			
			public State hDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint() ;
						draggedShape.translateBy(0, q.getY() - p.getY()) ;
						draggedShape.co
						p = q ;
					}
				} ;
				Transition release = new Release(BUTTON1, ">> start") {} ;
			};
			

		} ;
		sm.attachTo(canvas);

		pack() ;
		setVisible(true) ;
		canvas.requestFocusInWindow() ;
	}


	// ###############################################################################################
	// ###############################################################################################
	// ###############################################################################################
	public void populate() {
		int width = canvas.getWidth() ;
		int height = canvas.getHeight() ;

		double s = (Math.random()/2.0+0.5)*30.0 ;
		double x = s + Math.random()*(width-2*s) ;
		double y = s + Math.random()*(height-2*s) ;

		int red = (int)((0.8+Math.random()*0.2)*255) ;
		int green = (int)((0.8+Math.random()*0.2)*255) ;
		int blue = (int)((0.8+Math.random()*0.2)*255) ;

		CRectangle r = canvas.newRectangle(x,y,s,s) ;
		r.setFillPaint(new Color(red, green, blue)) ;
		r.addTag(oTag) ;
	}

	public static void main(String[] args) {
		MagneticGuides guides = new MagneticGuides("Magnetic guides",600,600) ;
		for (int i=0; i<20; ++i) guides.populate() ;
		guides.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
	}

}