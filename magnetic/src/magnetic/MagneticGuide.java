package magnetic;

import java.awt.BasicStroke;
import java.awt.Stroke;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.Canvas;

public class MagneticGuide extends CExtensionalTag {

	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;
	
	protected int type; 
	protected CSegment guide;
	
	public MagneticGuide(Canvas canvas){
		super(canvas);
	}
	
	/**
	 * @param position Y for horizontal guide, X for vertical guide
	 * @param type The type of the guide, must be {@code HORIZONTAL} or {@code VERTICAL}
	 * @param canvas
	 */
	public MagneticGuide(double position, int type, Canvas canvas) {
		this(canvas);
		
		if (type == HORIZONTAL)
			guide = new CSegment(canvas.getMinX() - 1000, position, canvas.getMaxX() + 1000, position);
		else if (type == VERTICAL)
			guide = new CSegment(position, canvas.getMinY() - 100, position, canvas.getMaxY() + 100);
		else 
			throw new IllegalArgumentException("Type of magnetic guide must be MagneticGuide.HORIZONTAL or MagneticGuide.VERTICAL");
		
		this.type = type;
        float[] dash = {10.f};
        Stroke stroke = new BasicStroke(1.f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 
        		10.f, dash, 0.f);
        guide.setStroke(stroke);
		guide.addTo(canvas);
		guide.belowAll();
	}
	
	public void addTagToGuide(CExtensionalTag tag) {
		guide.addTag(tag);
	}
	
	public int getType() { return type; }
}