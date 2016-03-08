package button;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;

import javax.swing.JFrame;

import fr.lri.swingstates.canvas.CRectangle;
import fr.lri.swingstates.canvas.CShape;
import fr.lri.swingstates.canvas.CStateMachine;
import fr.lri.swingstates.canvas.CText;
import fr.lri.swingstates.canvas.Canvas;
import fr.lri.swingstates.canvas.transitions.EnterOnShape;
import fr.lri.swingstates.canvas.transitions.LeaveOnShape;
import fr.lri.swingstates.canvas.transitions.PressOnShape;
import fr.lri.swingstates.sm.State;
import fr.lri.swingstates.sm.StateMachine;
import fr.lri.swingstates.sm.Transition;
import fr.lri.swingstates.sm.transitions.Release;

/**
 * @author Nicolas Roussel (roussel@lri.fr)
 *
 */
public class SimpleButton {

    private CText label ;
    private CRectangle rect;
    private CStateMachine stateMachine;
    @SuppressWarnings("unused")
    SimpleButton(Canvas canvas, String text) {
    	rect = canvas.newRectangle(0, 0, 50, 20);
    	label = canvas.newText(0, 0, text, new Font("comic sans", Font.PLAIN, 12)) ;
    	label.setParent(rect);
    	label.setPickable(false);
    	
    	stateMachine = new CStateMachine() {
    	    Paint initColor;
    	    public State out = new State() {
    	        Transition enterBox = new EnterOnShape(">> in") {
    	            public void action() {
    	                rect.setStroke(new BasicStroke(2));
    	            }
    	        };
    	    };
    	    public State in = new State() {
    	        Transition leaveBox = new LeaveOnShape(">> out") {
    	            public void action() {
    	                rect.setStroke(new BasicStroke(1));
    	            }
    	        };
    	        Transition press = new PressOnShape(">> inPress") {
    	        	public void action() {
    	        		initColor = getShape().getFillPaint();
    	                rect.setFillPaint(Color.YELLOW);
    	        	}
    	        };
    	    };
    	    public State inPress = new State() {
    	    	Transition leaveBox = new LeaveOnShape() {
    	            public void action() {
    	                rect.setStroke(new BasicStroke(1));
    	                rect.setFillPaint(initColor);
    	            }
    	        };
    	        Transition enterBox = new EnterOnShape() {
    	            public void action() {
    	                rect.setStroke(new BasicStroke(2));
    	                rect.setFillPaint(Color.YELLOW);
    	            }
    	        };
    	        Transition unpress = new Release(">> in") {
    	        	public void action() {
    	        		rect.setFillPaint(initColor);
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
	   JFrame frame = new JFrame() ;
	   Canvas canvas = new Canvas(400,400) ;
	   frame.getContentPane().add(canvas) ;
	   frame.pack();
	   frame.setVisible(true);
	   SimpleButton simple = new SimpleButton(canvas, "simple") ;
/*
	   JFrame frameViz = new JFrame("visualisation");
	   StateMachineVisualization viz = new StateMachineVisualization(simple.getStateMachine());
	   frameViz.setContentPane(viz);
	   frameViz.setVisible(true);
*/
	   simple.getShape().translateBy(200,200) ;
    }

}
