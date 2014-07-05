package visualgeclipse.ads.editors;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class VisualGColorManager {

	protected Map mColorMap = new HashMap(10);

	public void dispose() {
		Iterator e = mColorMap.values().iterator();
		while (e.hasNext())
			 ((Color) e.next()).dispose();
	}
	public Color getColor(RGB rgb) {
		Color color = (Color) mColorMap.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			mColorMap.put(rgb, color);
		}
		return color;
	}
}
