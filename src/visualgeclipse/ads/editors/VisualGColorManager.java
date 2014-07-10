package visualgeclipse.ads.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class VisualGColorManager {
	//Gereciamento de cores via HashMap
	private Map<RGB, Color> mColorMap = new HashMap<RGB, Color>(10);

	public void dispose() {
		Iterator<Color> e = mColorMap.values().iterator();
		while (e.hasNext()) {
			 e.next().dispose();
		}
	}
	
	public Color getColor(RGB rgb) {
		Color color = mColorMap.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			mColorMap.put(rgb, color);
		}
		return color;
	}
}
