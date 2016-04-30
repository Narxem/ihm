package magnetic;
import static magnetic.MagneticGuide.HORIZONTAL;
import static magnetic.MagneticGuide.VERTICAL;

import java.awt.Color ;
import java.awt.geom.Point2D ;

import javax.swing.JFrame ;

import fr.lri.swingstates.canvas.CExtensionalTag ;
import fr.lri.swingstates.canvas.CRectangle ;
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
	private CExtensionalTag hTag;
	private CExtensionalTag hGuideTag;
	private CExtensionalTag vTag;
	private CExtensionalTag vGuideTag;
	private CStateMachine state;

	public MagneticGuides(String title, int width, int height) {
		super(title) ;
		canvas = new Canvas(width, height) ;
		canvas.setAntialiased(true) ;
		getContentPane().add(canvas) ;

		oTag = new CExtensionalTag(canvas) {} ;
		hTag = new MagneticGuide(canvas);
		hGuideTag = new MagneticGuide(canvas);
		vTag = new MagneticGuide(canvas);
		vGuideTag = new MagneticGuide(canvas);
		

		@SuppressWarnings("unused")
		CStateMachine sm = new CStateMachine() {

			private Point2D p ;
			private CShape draggedShape ;

			public State start = new State() {
				Transition pressOnObject = new PressOnTag(oTag, BUTTON1, ">> oDrag") {
					public void action() {
						p = getPoint() ;
						draggedShape = getShape() ;
						if (draggedShape.hasTag(hTag)) {
							draggedShape.removeTag(hTag);
							draggedShape.getParent().removeChild(draggedShape);
							draggedShape.translateTo(p.getX(), p.getY());
						}
						if (draggedShape.hasTag(vTag)) {
							draggedShape.removeTag(vTag);
							draggedShape.getParent().removeChild(draggedShape);
							draggedShape.translateTo(p.getX(), p.getY());
						}
					}
				} ;
			
				// #####################################################################
				// Guides Creation
				Transition createHGuide = new Press(BUTTON1) {
					public void action() {
						p = getPoint() ;
						MagneticGuide guide = new MagneticGuide(p.getY(), HORIZONTAL, canvas);
						guide.addTagToGuide(hGuideTag);
					}
				};
				Transition createVGuide = new Press(BUTTON3) {
					public void action() {
						p = getPoint() ;
						MagneticGuide guide = new MagneticGuide(p.getX(), VERTICAL, canvas);
						guide.addTagToGuide(vGuideTag);
					}
				};
				
				// #####################################################################
				// Move guides
				Transition pressOnHGuide = new PressOnTag(hGuideTag, BUTTON1, ">> hDrag") {
					public void action() {
						p = getPoint();
						draggedShape = getShape();
					}
				};
				Transition pressOnVGuide = new PressOnTag(vGuideTag, BUTTON1, ">> vDrag") {
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
				Transition release = new Release(BUTTON1, ">> start") {
					public void action() {
						p = getPoint();
						for (CShape shapeH : hGuideTag.getFilledShapes()) {
							if (draggedShape.contains(draggedShape.getCenterX(), shapeH.getMinY()) != null) {
								draggedShape.addTag(hTag);
								shapeH.addChild(draggedShape);
								draggedShape.translateBy(0, shapeH.getCenterY() - draggedShape.getCenterY());
							}
						}
						for (CShape shapeV : vGuideTag.getFilledShapes()) {
							if (draggedShape.contains(shapeV.getMinX(), draggedShape.getCenterY()) != null) {
								draggedShape.addTag(vTag);
								shapeV.addChild(draggedShape);
								draggedShape.translateBy(shapeV.getCenterX() - draggedShape.getCenterX(), 0);
							}
						}
					}
				} ;
			} ;
			
			public State hDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint() ;
						draggedShape.translateBy(0, q.getY() - p.getY()) ;
						p = q ;
					}
				} ;
				Transition release = new Release(BUTTON1, ">> start") {} ;
			};
			public State vDrag = new State() {
				Transition drag = new Drag(BUTTON1) {
					public void action() {
						Point2D q = getPoint() ;
						draggedShape.translateBy(q.getX() - p.getX(), 0) ;
						p = q ;
					}
				} ;
				Transition release = new Release(BUTTON1, ">> start") {} ;
			};
			

		} ;
		this.state = sm;
		sm.attachTo(canvas);

		pack() ;
		setVisible(true) ;
		canvas.requestFocusInWindow() ;
	}


	public CStateMachine getStateMachine() {
		return state;
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