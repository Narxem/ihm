package magnetic;

import fr.lri.swingstates.canvas.CExtensionalTag;
import fr.lri.swingstates.canvas.CSegment;
import fr.lri.swingstates.canvas.Canvas;

public class MagneticGuide extends CExtensionalTag {

	private CSegment segment;
	
	public MagneticGuide(Canvas canvas) {
		segment = new CSegment();
	}

}
