package button;


import static javax.swing.JFrame.EXIT_ON_CLOSE;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;

import javax.swing.JFrame;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.canvas.transitions.EnterOnShape;
import fr.lri.swingstates.canvas.transitions.LeaveOnShape;
import fr.lri.swingstates.canvas.transitions.PressOnShape;
import fr.lri.swingstates.debug.StateMachineVisualization;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.StateMachine;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Release;
import fr.lri.swingstates.sm.transitions.TimeOut;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 */
public class TimerButton {

    protected static final int DELAY = 1000; // Demi clic effectué si bouton enfoncé pendant une seconde
	private CText label ;
    private CRectangle rect;
    private CStateMachine stateMachine;
   
    
    private static CExtensionalTag mouseOnShapeTag = new CExtensionalTag() {
    	@Override
    	public void added(CShape s) {
    		s.setStroke(new BasicStroke(2));
    	}
    	
    	@Override
    	public void removed(CShape s) {
    		s.setStroke(new BasicStroke(1));
    	}
    };
    
    
    private static CExtensionalTag clickedTag = new CExtensionalTag() {
    	Paint initColor;
    	
    	@Override
    	public void added(CShape s) {
    		initColor = s.getFillPaint();
    		s.setFillPaint(Color.YELLOW);
    	}
    	
    	public void removed(CShape s) {
    		s.setFillPaint(initColor);
    	}
    };
    
    @SuppressWarnings("unused")
    TimerButton(Canvas canvas, String text) {
    	/* **************************************
    	 * Question 2 */
    	rect = canvas.newRectangle(0, 0, 50, 20);
    	label = canvas.newText(0, 0, text, new Font("comic sans", Font.PLAIN, 12)) ;
    	label.setParent(rect);
    	label.setPickable(false);
    	
    	stateMachine = new CStateMachine() {
    	    Paint initColor;
    	    public State out = new State() {
    	        Transition enterBox = new EnterOnShape(">> in") {
    	            public void action() {
    	                rect.addTag(mouseOnShapeTag);
    	            }
    	        };
    	    };
    	    
    	    public State in = new State() {
    	        Transition leaveBox = new LeaveOnShape(">> out") {
    	            public void action() {
    	                rect.removeTag(mouseOnShapeTag);
    	            }
    	        };
    	        Transition press = new PressOnShape(">> inPress") {
    	        	public void action() {
    	                rect.addTag(clickedTag);
    	                armTimer(DELAY, false);
    	        	}
    	        };
    	    };
    	    
    	    public State inPress = new State() {
    	    	Transition leaveBox = new LeaveOnShape(">> outPress") {
    	            public void action() {
    	                rect.removeTag(mouseOnShapeTag);
    	                rect.removeTag(clickedTag);
    	            }
    	        };
    	        Transition unpress = new Release(">> in") {
    	        	public void action() {
    	        		rect.removeTag(clickedTag);
    	        	}
    	        };
    	        Transition hold = new TimeOut(">> in") {
    	        	public void action() {
    	        		rect.removeTag(clickedTag);
    	        		System.out.println("Demi-clic");
    	        	}
    	        };
    	    };
    	    
    	    public State outPress = new State() {
    	    	Transition onBox = new EnterOnShape(">> inPress") {
    	    		public void action() {
    	    			rect.addTag(clickedTag);
    	    			rect.addTag(mouseOnShapeTag);
    	    		}
    	    	};
    	    	Transition release = new Release(">> out") {};
    	    	Transition hold = new TimeOut(">> out") {
    	    		public void action() {
    	    			System.out.println("Demi-clic");
    	    		}
    	    	};
    	    };
    	};
    	stateMachine.attachTo(rect);
    }

    
    public StateMachine getStateMachine() {
    	return stateMachine;
    }
    
    public void action() {
	   System.out.println("ACTION!") ;
    }

    public CShape getShape() {
	   return rect ;
    }

    static public void main(String[] args) {
	   JFrame frame = new JFrame("Timer button") ;
	   Canvas canvas = new Canvas(200, 200) ;
	   frame.getContentPane().add(canvas) ;
	   frame.pack();
	   frame.setLocationRelativeTo(null);
	   frame.setVisible(true);
	   TimerButton simple = new TimerButton(canvas, "simple") ;
	   frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	   JFrame frameViz = new JFrame("visualisation");
	   StateMachineVisualization viz = new StateMachineVisualization(simple.getStateMachine());
	   frameViz.setContentPane(viz);
	   frameViz.setVisible(true);
	   frameViz.pack();

	   simple.getShape().translateBy(100, 100) ;
    }

}
